package com.searchablespinnerlibrary

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupWindow
import androidx.recyclerview.widget.RecyclerView
import com.searchablespinnerlibrary.databinding.CustomSearchSpinnerBinding

fun View.showSearchablePopupAbove(
    itemList: ArrayList<String>,
    onItemSelected: (name: String) -> Unit
) {
    val popupView = CustomSearchSpinnerBinding.inflate(LayoutInflater.from(this.context), null, false).root
    val popupWindow = PopupWindow(popupView, this.width, ViewGroup.LayoutParams.WRAP_CONTENT, true)
    val editText = popupView.findViewById<EditText>(R.id.editTextSearch)
    val rvItems = popupView.findViewById<RecyclerView>(R.id.rvItems)
    val adapter = SpinnerAdapter(itemList) { name ->
        onItemSelected(name)
        popupWindow.dismiss()
    }
    rvItems.adapter = adapter
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Create a new filtered list
            val filterList = ArrayList<String>().apply {
                // Only apply filter if search string is not empty
                if (!s.isNullOrEmpty()) {
                    for (value in itemList) {
                        if (value.contains(
                                s.toString(),
                                true
                            )
                        ) {
                            add(value) // Add to filter list
                        }
                    }
                } else {
                    // If empty, reset to the original item list (just return an empty list)
                    addAll(itemList)
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

fun Activity.openSpinnerActivity(){
    val intent = Intent(this, SpinnerActivity::class.java)
    startActivity(intent)
}