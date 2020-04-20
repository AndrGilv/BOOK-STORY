package com.e.bookstory.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.e.bookstory.entities.Book

class DatabaseHelper(context: Context): SQLiteOpenHelper(context,
    DB_NAME, null,
    DB_VERSION
) {

    companion object{
        private val DB_NAME = "bookstory_local_db"
        private val DB_VERSION = 1
    }



    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE BOOK(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT);")// И ТАК ДАЛЕЕ
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertBook(db: SQLiteDatabase, book:Book){
        val bookItem: ContentValues = ContentValues()
        bookItem.put("TITLE", book.title)
        //и так далее
        db.insert("BOOK", null, bookItem)
    }

    fun updateBook(db: SQLiteDatabase, book:Book, id: String){
        val bookItem: ContentValues = ContentValues()
        bookItem.put("TITLE", book.title)
        //и так далее
        db.update("BOOK", bookItem, "_id = ?", Array<String>(1){ id})
    }

    fun deleteBook(db: SQLiteDatabase, id: String){
        db.delete("BOOK","_id = ?", Array<String>(1){ id})
    }


}