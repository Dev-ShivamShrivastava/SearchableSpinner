package com.searchablespinnerlibrary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.searchablespinnerlibrary.databinding.CustomSpinnerItemBinding

class SpinnerAdapter(private var items: ArrayList<String>, val onItemSelected:(name: String)->Unit ):
    RecyclerView.Adapter<SpinnerAdapter.SpinnerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpinnerViewHolder {
        val binding = CustomSpinnerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SpinnerViewHolder(binding)
    }
    override fun onBindViewHolder(holder: SpinnerViewHolder, position: Int) {

        // Access the value using the key
        val value = items[position]
        holder.binding.tvView.text =value
        holder.itemView.setOnClickListener {
            onItemSelected(value)
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
    fun setFilterData(filterData: ArrayList<String>){
        items = filterData
        notifyDataSetChanged()
    }
    class SpinnerViewHolder(val binding:CustomSpinnerItemBinding):RecyclerView.ViewHolder(binding.root){}
}