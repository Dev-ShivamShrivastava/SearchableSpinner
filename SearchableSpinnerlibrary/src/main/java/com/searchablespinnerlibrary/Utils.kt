package com.searchablespinnerlibrary

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.TextView
import androidx.collection.ArrayMap
import androidx.recyclerview.widget.RecyclerView
import com.searchablespinnerlibrary.databinding.CustomSearchSpinnerBinding

fun View.showSearchablePopupAbove(
    itemList: ArrayMap<String, String>, header: String,
    onItemSelected: (id: String, name: String) -> Unit
) {
    val popupView = CustomSearchSpinnerBinding.inflate(LayoutInflater.from(this.context), null, false).root
    val popupWindow = PopupWindow(popupView, this.width, ViewGroup.LayoutParams.WRAP_CONTENT, true)
    val editText = popupView.findViewById<EditText>(R.id.editTextSearch)
    val rvItems = popupView.findViewById<RecyclerView>(R.id.rvItems)
    val tvHeader = popupView.findViewById<TextView>(R.id.tvViewHeader)
    tvHeader.text = header
    tvHeader.setOnClickListener {
        onItemSelected("-1", header)
        popupWindow.dismiss()
    }
    val adapter = SpinnerAdapter(itemList) { id, name ->
        onItemSelected(id, name)
        popupWindow.dismiss()
    }
    rvItems.adapter = adapter
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Create a new filtered list
            val filterList = ArrayMap<String, String>().apply {
                // Only apply filter if search string is not empty
                if (!s.isNullOrEmpty()) {
                    for ((key, value) in itemList) {
                        if (key.contains(s.toString(), true) || value.contains(
                                s.toString(),
                                true
                            )
                        ) {
                            put(key, value) // Add to filter list
                        }
                    }
                } else {
                    // If empty, reset to the original item list (just return an empty list)
                    putAll(itemList as Map<String, String>)
                }
            }
            adapter.setFilterData(filterList) // Set filtered data to adapter
        }
        override fun afterTextChanged(s: Editable?) {}
    })
    val location = IntArray(2)
    this.getLocationOnScreen(location)
    popupWindow.showAsDropDown(this, 0, -this.getHeight() + popupView.getHeight())
}