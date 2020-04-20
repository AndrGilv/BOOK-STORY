package com.e.bookstory.activityAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.R
import com.e.bookstory.activities.BookActivity
import com.e.bookstory.entities.Book
import com.squareup.picasso.Picasso

class BooksInOrderRVAdapter(bookList: List<Book>, context: Context) : RecyclerView.Adapter<BooksInOrderRVAdapter.BookViewHolder>() {

    var bookList: List<Book>
    var context: Context

    init {
        this.bookList = bookList
        this.context = context
    }

    class BookViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        val genreTextView: TextView
        val bookTitleTextView: TextView
        val amountNumbTextView: TextView
        val costTextView: TextView
        val bookImage: ImageView

        lateinit var book: Book

        init {
            genreTextView = itemView.findViewById<TextView>(R.id.genreTextView)
            bookTitleTextView = itemView.findViewById<TextView>(R.id.bookTitleTextView)
            amountNumbTextView = itemView.findViewById<TextView>(R.id.amountNumbTextView)
            costTextView = itemView.findViewById<TextView>(R.id.costTextView)
            bookImage = itemView.findViewById<ImageView>(R.id.bookImage)



            Picasso.get()
                .load(R.drawable.under_the_dome_cover) //сдесь должен быть url на обложку книги book.bookImgURL
                .placeholder(R.drawable.book_placeholder)
                .into(bookImage)

            bookTitleTextView.setOnClickListener {
                val intent = Intent(context, BookActivity::class.java)
                intent.putExtra("book", book.getParams())
                startActivity(context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.book_view_layout, parent, false)
        val pvh: BookViewHolder =
            BookViewHolder(
                v,
                context
            )
        return pvh;
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.genreTextView.text = bookList[position].genre
        holder.amountNumbTextView.text = "немного"
        holder.bookTitleTextView.text = bookList[position].title
        holder.costTextView.text = bookList[position].price.toString()
        holder.book = bookList[position]
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }
}