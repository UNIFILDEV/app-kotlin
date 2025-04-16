package com.example.listadecompras

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var db: DBHelper
    lateinit var listView: ListView
    lateinit var adapter: ArrayAdapter<String>
    var lists = listOf<ShoppingList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = DBHelper(this)
        listView = findViewById(R.id.listView)

        // Configura o ImageButton para adicionar uma nova lista
        val fabAdd: ImageButton = findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener {
            val input = EditText(this)
            AlertDialog.Builder(this)
                .setTitle("Nova Lista")
                .setView(input)
                .setPositiveButton("Salvar") { _, _ ->
                    db.createList(input.text.toString())
                    loadLists()
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

        listView.setOnItemClickListener { _, _, i, _ ->
            val intent = Intent(this, ItemsActivity::class.java)
            intent.putExtra("listId", lists[i].id)
            startActivity(intent)
        }

        loadLists()
    }

    private fun loadLists() {
        lists = db.getLists()
        val nomes = lists.map { it.name }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nomes)
        listView.adapter = adapter
    }
}
