package com.example.listadecompras

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ItemsActivity : AppCompatActivity() {
    lateinit var db: DBHelper
    lateinit var listView: ListView
    var items = listOf<Item>()
    var listId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        db = DBHelper(this)
        listView = findViewById(R.id.itemsList)
        listId = intent.getIntExtra("listId", 0)

        findViewById<Button>(R.id.btnAddItem).setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            intent.putExtra("listId", listId)
            startActivity(intent)
        }

        listView.setOnItemLongClickListener { _, _, i, _ ->
            db.deleteItem(items[i].id)
            loadItems()
            true
        }
    }

    override fun onResume() {
        super.onResume()
        loadItems()
    }

    private fun loadItems() {
        items = db.getItems(listId)
        val nomes = items.map { "${it.name} (x${it.quantity})" }
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nomes)
    }
}
