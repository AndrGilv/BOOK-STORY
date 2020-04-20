package com.e.bookstory.activityAdapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.BottomNavBar
import com.e.bookstory.R
import com.e.bookstory.activities.BookActivity
import com.e.bookstory.entities.Book
import com.e.bookstory.entities.Purchase
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso
import java.io.Serializable


class CartRVAdapter(purchasesList: ArrayList<Purchase>, context: Context): RecyclerView.Adapter<CartRVAdapter.BookViewHolder>(), Serializable{

    @SerializedName("purchasesList")
    var purchasesList: ArrayList<Purchase>
    @SerializedName("context")
    var context: Context

    init {
        this.purchasesList = purchasesList
        this.context = context
    }

    class BookViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        @Transient
        var cv: CardView
        @Transient
        var title: TextView
        @Transient
        var genreTView: TextView
        @Transient
        var descriptionEditText: TextView
        @Transient
        var costTView: TextView
        @Transient
        var ratingBar: RatingBar
        //@Transient
        lateinit var book: Book
        @Transient
        var bookImg: ImageView
        @Transient
        var bookCount: TextView
        @Transient
        val addBookBtn: ImageButton
        @Transient
        val removeBookBtn: ImageButton
        @Transient
        val removeFromCartBtn: Button

        var amountOfBook: Int = 1
        @Transient
        val context: Context
        @Transient
        val bottomNuvBar: BottomNavBar
        @Transient
        lateinit var purchase: Purchase

        init {
            cv = itemView.findViewById(R.id.bookCard)
            title = itemView.findViewById(R.id.bookTitleTView)
            genreTView = itemView.findViewById(R.id.genreTView)
            descriptionEditText = itemView.findViewById(R.id.descriptionEditText)
            costTView = itemView.findViewById(R.id.costTView)
            ratingBar = itemView.findViewById(R.id.ratingBar)

            bookImg = itemView.findViewById(R.id.bookImg)

            addBookBtn = itemView.findViewById(R.id.cart_addBookButton)
            bookCount = itemView.findViewById(R.id.cart_amountNumbTView)
            removeBookBtn = itemView.findViewById(R.id.cart_removeBookButton)
            removeFromCartBtn = itemView.findViewById(R.id.removeFromCartBtn)

            this.context = context
            bottomNuvBar = BottomNavBar.getInstance()!!

            Picasso.get()
                .load(R.drawable.under_the_dome_cover) //сдесь должен быть url на обложку книги book.bookImgURL
                .placeholder(R.drawable.book_placeholder)
                .into(bookImg)

            ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser -> run{
                Toast.makeText(itemView.context, rating.toString(), Toast.LENGTH_LONG).show()
            } }

            /*title.setOnClickListener {
                val intent = Intent(context, BookActivity::class.java)
                intent.putExtra("book", book.getParams())
                ContextCompat.startActivity(context, intent, null)
            }*/

            title.setOnClickListener(this::moveToBookInfoBtnListener)
            descriptionEditText.setOnClickListener(this::moveToBookInfoBtnListener)
            bookImg.setOnClickListener(this::moveToBookInfoBtnListener)

            addBookBtn.setOnClickListener {
                amountOfBook++
                bookCount.text = amountOfBook.toString()
                purchase.count = amountOfBook
            }

            removeBookBtn.setOnClickListener {
                if(amountOfBook > 1){
                    amountOfBook--
                    bookCount.text = amountOfBook.toString()
                    purchase.count = amountOfBook
                }
            }

            removeFromCartBtn.setOnClickListener {

                bottomNuvBar.decrementCartBadge()
            }
        }

        fun moveToBookInfoBtnListener(view: View){
            val intent = Intent(context, BookActivity::class.java)
            intent.putExtra("book", book.getParams())
            ContextCompat.startActivity(context, intent, null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.cart_book_card_layout,parent, false)
        val pvh: BookViewHolder =
            BookViewHolder(
                v,
                context
            )
        return pvh;
    }

    override fun getItemCount(): Int {
        return purchasesList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.title.text = purchasesList[position].book.title
        holder.ratingBar.rating = purchasesList[position].book.rating.toFloat()
        holder.genreTView.text = purchasesList[position].book.genre
        holder.descriptionEditText.text = purchasesList[position].book.description
        holder.costTView.text = (purchasesList[position].book.price.toString() + " руб.")
        holder.book = purchasesList[position].book
        holder.bookCount.text = purchasesList[position].count.toString()

        holder.purchase = purchasesList[position]

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }


}