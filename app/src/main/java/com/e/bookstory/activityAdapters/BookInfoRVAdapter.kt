package com.e.bookstory.activityAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.R
import com.e.bookstory.entities.Book


class BookInfoRVAdapter(book: Book, context: Context): RecyclerView.Adapter<BookInfoRVAdapter.BookViewHolder>(){
    var bookInfo: Array<String>
    var bookInfoLables:  Array<String>
    init {
        this.bookInfo = arrayOf(book.title, book.author, book.price.toString(), book.pageNum.toString(),
            book.editor, book.cover, book.weight.toString(), book.genre, book.publisher, book.series,
            book.translator, book.language, book.date.toString(), book.height.toString(), book.length.toString(),
            book.width.toString(), book.rating.toString(), book.url.toString(), book.description)

        this.bookInfoLables = arrayOf("Заголовок", "Автор", "Цена", "Количесвто страниц",
            "Редактор", "Тип обложки", "Вес книги", "Жанр", "Издатель", "Серия",
            "Перевёл", "Язык", "Дата выхода", "Высота", "Ширина",
            "Толщина", "Рейтинг книги", "Ссылка на книгу", "Описание")
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var bookInfoTV: TextView
        var bookInfoLable: TextView

        init {
            bookInfoTV = itemView.findViewById(R.id.bookInfoTView)
            bookInfoLable = itemView.findViewById(R.id.bookInfoLable)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.bookinfo_layout,parent, false)
        val pvh: BookViewHolder =
            BookViewHolder(v)
        return pvh;
    }

    override fun getItemCount(): Int {
        return bookInfo.count()
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bookInfoTV.text = bookInfo[position]
        holder.bookInfoLable.text = bookInfoLables[position]
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }
}