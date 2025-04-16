package com.example.listadecompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(
    private val listas: List<ShoppingList>,
    private val onClick: (ShoppingList) -> Unit
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeLista: TextView = itemView.findViewById(R.id.textNomeLista)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lista_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val lista = listas[position]
        holder.nomeLista.text = lista.name
        holder.itemView.setOnClickListener { onClick(lista) }
    }

    override fun getItemCount() = listas.size
}
