package com.example.listadecompras.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.R
import com.example.listadecompras.adapter.ShoppingListAdapter
import com.example.listadecompras.data.ShoppingItem


class MainActivity : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editQty: EditText
    private lateinit var btnSave: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShoppingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editName = findViewById(R.id.editName)
        editQty = findViewById(R.id.editQty)
        btnSave = findViewById(R.id.btnSave)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = ShoppingListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnSave.setOnClickListener {
            val name = editName.text.toString()
            val qty = editQty.text.toString().toIntOrNull() ?: 1

            if (name.isNotBlank()) {
                val item = ShoppingItem(name = name, quantity = qty)
                adapter.addItem(item)

                editName.text.clear()
                editQty.text.clear()
            }
        }
    }
}
