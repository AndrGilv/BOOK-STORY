package com.e.bookstory.activityAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.BottomNavBar
import com.e.bookstory.R
import com.e.bookstory.activities.BookActivity
import com.e.bookstory.entities.Book
import com.squareup.picasso.Picasso


class CatalogRVAdapter(bookList: List<Book>, context: Context): RecyclerView.Adapter<CatalogRVAdapter.BookViewHolder>(){
    var bookList: List<Book>
    var context: Context



    init {
        this.bookList = bookList
        this.context = context
    }

    class BookViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        var cv: CardView
        var title: TextView
        var genreTView: TextView
        var descriptionEditText: TextView
        var costTView: TextView
        var ratingBar: RatingBar
        var bookImg: ImageView
        val addBookBtn: ImageButton
        val amountNumbT: TextView
        val removeBookBtn: ImageButton
        val addToCartBtn: Button
        lateinit var book: Book

        var amountOfBook: Int = 1
        val context: Context
        val bottomNuvBar: BottomNavBar

        init {
            cv = itemView.findViewById(R.id.bookCard)
            title = itemView.findViewById(R.id.bookTitleTView)
            genreTView = itemView.findViewById(R.id.genreTView)
            descriptionEditText = itemView.findViewById(R.id.descriptionEditText)
            costTView = itemView.findViewById(R.id.costTView)
            ratingBar = itemView.findViewById(R.id.ratingBar)
            bookImg = itemView.findViewById(R.id.bookImg)
            addBookBtn = itemView.findViewById(R.id.addBookButton)
            amountNumbT = itemView.findViewById(R.id.amountNumbTView)
            removeBookBtn = itemView.findViewById(R.id.removeBookButton)
            addToCartBtn = itemView.findViewById(R.id.addToCartBtn)

            this.context = context
            bottomNuvBar = BottomNavBar.getInstance()!!

            Picasso.get()
                .load(R.drawable.under_the_dome_cover) //сдесь должен быть url на обложку книги book.bookImgURL
                .placeholder(R.drawable.book_placeholder)
                .into(bookImg)

            ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser -> run{
                Toast.makeText(itemView.context, rating.toString(), Toast.LENGTH_LONG).show()
            } }

            title.setOnClickListener(this::moveToBookInfoBtnListener)
            descriptionEditText.setOnClickListener(this::moveToBookInfoBtnListener)
            bookImg.setOnClickListener(this::moveToBookInfoBtnListener)

            addBookBtn.setOnClickListener {
                amountOfBook++
                amountNumbT.text = amountOfBook.toString()
            }

            removeBookBtn.setOnClickListener {
                if(amountOfBook > 1){
                    amountOfBook--
                    amountNumbT.text = amountOfBook.toString()
                }
            }

            addToCartBtn.setOnClickListener {
                bottomNuvBar.incrementCartBadge()
            }
        }

        fun moveToBookInfoBtnListener(view: View){
            val intent = Intent(context, BookActivity::class.java)
            intent.putExtra("book", book.getParams())
            startActivity(context, intent, null)
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.book_card_layout,parent, false)
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
        holder.title.text = bookList[position].title
        holder.ratingBar.rating = bookList[position].rating.toFloat()
        holder.genreTView.text = bookList[position].genre
        holder.descriptionEditText.text = bookList[position].description
        holder.costTView.text = (bookList[position].price.toString() + " руб.")
        holder.book = bookList[position]
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }
}