package com.example.listadecompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val items: List<Item>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeText: TextView = itemView.findViewById(R.id.textItemNome)
        val quantidadeText: TextView = itemView.findViewById(R.id.textItemQuantidade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lista, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.nomeText.text = item.name              // Corrigido: name
        holder.quantidadeText.text = "Qtd: ${item.quantity}" // Corrigido: quantity
    }

    override fun getItemCount() = items.size
}
