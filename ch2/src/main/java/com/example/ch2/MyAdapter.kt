package com.example.ch2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ch2.databinding.ItemMainBinding

class MyViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)
class MyAdapter(val context: Context, val itemList: MutableList<ItemData>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = itemList.get(position)
        holder.binding.run {
            tvItemEmail.text = data.email
            tvItemDate.text = data.date
            tvItemContent.text = data.content
        }
    }

    override fun getItemCount() = itemList.size

}