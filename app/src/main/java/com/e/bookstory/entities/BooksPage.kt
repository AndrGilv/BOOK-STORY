package com.e.bookstory.entities

import com.google.gson.annotations.SerializedName

data class BooksPage(@SerializedName("content") var content: ArrayList<Book>) {
}