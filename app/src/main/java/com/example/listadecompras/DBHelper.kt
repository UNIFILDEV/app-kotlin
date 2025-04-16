package com.example.listadecompras

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "ShoppingDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE shopping_lists(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)")
        db.execSQL("CREATE TABLE items(id INTEGER PRIMARY KEY AUTOINCREMENT, list_id INTEGER, name TEXT, quantity INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS shopping_lists")
        db.execSQL("DROP TABLE IF EXISTS items")
        onCreate(db)
    }

    fun createList(name: String) {
        writableDatabase.execSQL("INSERT INTO shopping_lists(name) VALUES (?)", arrayOf(name))
    }

    fun getLists(): List<ShoppingList> {
        val cursor = readableDatabase.rawQuery("SELECT * FROM shopping_lists", null)
        val lists = mutableListOf<ShoppingList>()
        while (cursor.moveToNext()) {
            lists.add(ShoppingList(cursor.getInt(0), cursor.getString(1)))
        }
        cursor.close()
        return lists
    }

    fun createItem(listId: Int, name: String, quantity: Int) {
        writableDatabase.execSQL("INSERT INTO items(list_id, name, quantity) VALUES (?, ?, ?)", arrayOf(listId, name, quantity))
    }

    fun getItems(listId: Int): List<Item> {
        val cursor = readableDatabase.rawQuery("SELECT * FROM items WHERE list_id=?", arrayOf(listId.toString()))
        val items = mutableListOf<Item>()
        while (cursor.moveToNext()) {
            items.add(Item(cursor.getInt(0), listId, cursor.getString(2), cursor.getInt(3)))
        }
        cursor.close()
        return items
    }

    fun deleteItem(id: Int) {
        writableDatabase.execSQL("DELETE FROM items WHERE id=?", arrayOf(id))
    }
}
