package com.searchablespinnerlibrary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.recyclerview.widget.RecyclerView
import com.searchablespinnerlibrary.databinding.CustomSpinnerItemBinding

class SpinnerAdapter(private var items: ArrayMap<String, String>, val onItemSelected:(id: String, name: String)->Unit ):
    RecyclerView.Adapter<SpinnerAdapter.SpinnerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpinnerViewHolder {
        val binding = CustomSpinnerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SpinnerViewHolder(binding)
    }
    override fun onBindViewHolder(holder: SpinnerViewHolder, position: Int) {
        // Access the key at the current position
        val key = items.keyAt(position)
        // Access the value using the key
        val value = items[key]
        holder.binding.tvView.text =value
        holder.itemView.setOnClickListener {
            println("key-> $key and value-> $value")
            onItemSelected(key,value?:"")
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
    fun setFilterData(filterData: ArrayMap<String, String>){
        items = filterData
        notifyDataSetChanged()
    }
    class SpinnerViewHolder(val binding:CustomSpinnerItemBinding):RecyclerView.ViewHolder(binding.root){}
}