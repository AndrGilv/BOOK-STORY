package com.e.bookstory.entities

import com.google.gson.annotations.SerializedName
import java.net.URL
import java.util.*

data class Book(@SerializedName("bookId") var bookID: Int,
                @SerializedName("title") var title: String,
                @SerializedName("author") var author: String,
                @SerializedName("price") var price: Int,
                @SerializedName("pageNumber") var pageNum: Int,
                @SerializedName("editor") var editor: String,
                @SerializedName("cover") var cover: String,
                @SerializedName("weight") var weight: Double,
                @SerializedName("genre") var genre: String,
                @SerializedName("publisher") var publisher: String,
                @SerializedName("series") var series: String,
                @SerializedName("translator") var translator:  String,
                @SerializedName("language") var language: String,
                @SerializedName("date") var date: Int,
                @SerializedName("height") var height: Int,
                @SerializedName("width") var width: Int,
                @SerializedName("length") var length: Int,
                @SerializedName("rating") var rating: Int,
                @SerializedName("url") var url: URL,
                @SerializedName("description") var description: String){ // description пока нет

    constructor() : this(1, "Книга-хуига", "Автор Хуявтор", 777, 100500,
        "Едитор хуитор", "Мягкая", 50.0, "Фантастика",
        "Издатель хуятель", "Серия хуерия", "Переводчик хуётчик", "Пендосский",
        222, 20, 20, 20, 3, URL("https://kotlinlang.org/"),
        "Какое-то описание просто ну капец длинное описание книги, никогда токого небыло и вот опять просто " +
                "пиздец Какое-то описание просто ну капец длинное описание книги, никогда токого небыло и вот опять просто пиздец" +
                "Какое-то описание просто ну капец длинное описание книги, никогда токого небыло и вот опять просто пиздец")

    /*var bookID: Int
    var title: String
    var author: String
    var price: Int
    var pageNum: Int
    var editor: String
    var cover: String
    var weight: Double
    var genre: String
    var publisher: String
    var series: String
    var translator:  String
    var language: String
    var date: Date
    var height: Int
    var width: Int
    var length: Int
    var rating: Int
    var url: URL
    var descriprtion: String*/

    /*init {
        this.bookID = bookID //0
        this.title = title //1
        this.author = author //2
        this.price = price //3
        this.pageNum = pageNum //4
        this.editor = editor //5
        this.cover = cover //6
        this.weight = weight //7
        this.genre = genre  //8
        this.publisher = publisher //9
        this.series = series //10
        this.translator = translator //11
        this.language = language // 12
        this.date = date //13
        this.height = height //14
        this.width = width //15
        this.length = length //16
        this.rating = rating //17
        this.url = url //18
        this.descriprtion = descriprtion //19
    }*/

    companion object {
        fun bildBookByParams(arr: Array<String>): Book {
            return Book(
                arr[0].toInt(),
                arr[1],
                arr[2],
                arr[3].toInt(),
                arr[4].toInt(),
                arr[5],
                arr[6],
                arr[7].toDouble(),
                arr[8],
                arr[9],
                arr[10],
                arr[11],
                arr[12],
                arr[13].toInt(),//data
                arr[14].toInt(),
                arr[15].toInt(),
                arr[16].toInt(),
                arr[17].toInt(),
                URL(arr[18]),
                arr[19]
            )
        }
    }

    public fun getParams(): Array<String> {
        val arr: Array<String> = arrayOf(bookID.toString(), title, author, price.toString(), pageNum.toString(), editor,
            cover, weight.toString(), genre, publisher, series, translator, language, date.toString(), height.toString(),
            width.toString(), length.toString(), rating.toString(), url.toString(), description)
        return arr
    }


}