package com.example.listadecompras.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.ItemShoppingBinding
import com.example.listadecompras.data.ShoppingItem

class ShoppingListAdapter : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    private val list = mutableListOf<ShoppingItem>()

    fun addItem(item: ShoppingItem) {
        list.add(item)
        notifyItemInserted(list.size - 1)
    }

    class ViewHolder(val binding: ItemShoppingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShoppingItem) {
            binding.txtItemName.text = item.name
            binding.txtItemQty.text = "Qtd: ${item.quantity}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemShoppingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
