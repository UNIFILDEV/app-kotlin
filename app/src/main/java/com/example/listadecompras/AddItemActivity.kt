package com.example.listadecompras

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddItemActivity : AppCompatActivity() {
    lateinit var db: DBHelper
    var listId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        db = DBHelper(this)
        listId = intent.getIntExtra("listId", 0)

        val name = findViewById<EditText>(R.id.editItem)
        val qty = findViewById<EditText>(R.id.editQty)
        val btnSave = findViewById<Button>(R.id.btnSaveItem)

        btnSave.setOnClickListener {
            val itemName = name.text.toString()
            val itemQty = qty.text.toString().toIntOrNull() ?: 1
            db.createItem(listId, itemName, itemQty)
            finish()
        }
    }
}
