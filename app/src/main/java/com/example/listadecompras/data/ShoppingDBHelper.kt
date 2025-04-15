package com.example.listadecompras.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class ShoppingDBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "shopping.db"
        private const val DB_VERSION = 1

        const val TABLE_NAME = "items"
        const val COL_ID = "id"
        const val COL_NAME = "name"
        const val COL_QTY = "quantity"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql = """
            CREATE TABLE $TABLE_NAME (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NAME TEXT,
                $COL_QTY INTEGER
            )
        """
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insert(item: ShoppingItem): Long {
        val values = ContentValues().apply {
            put(COL_NAME, item.name)
            put(COL_QTY, item.quantity)
        }
        return writableDatabase.insert(TABLE_NAME, null, values)
    }

    fun update(item: ShoppingItem): Int {
        val values = ContentValues().apply {
            put(COL_NAME, item.name)
            put(COL_QTY, item.quantity)
        }
        return writableDatabase.update(TABLE_NAME, values, "$COL_ID=?", arrayOf(item.id.toString()))
    }

    fun delete(id: Int): Int {
        return writableDatabase.delete(TABLE_NAME, "$COL_ID=?", arrayOf(id.toString()))
    }

    fun getAll(): List<ShoppingItem> {
        val list = mutableListOf<ShoppingItem>()
        val cursor = readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val item = ShoppingItem(
                    id = cursor.getInt(0),
                    name = cursor.getString(1),
                    quantity = cursor.getInt(2)
                )
                list.add(item)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}
