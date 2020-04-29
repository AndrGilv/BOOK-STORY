package com.e.bookstory.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.e.bookstory.*
import com.e.bookstory.activities.views.BookActivityView
import com.e.bookstory.presenters.BookActivityPresenter
import com.e.bookstory.presenters.CartFragmentPresenter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_book.*

class BookActivity : AppCompatActivity(), BookActivityView {
    lateinit var bookParams: Array<String>
    private val BOOK_PARAMS_KEY: String = "bookParams"

    private lateinit var avatarImageView: ImageView
    private lateinit var bookImageView: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var descriptionText: TextView

    private lateinit var presenter: BookActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        presenter = BookActivityPresenter(this)
        presenter.attachView(this)
        val toolBar: Toolbar = findViewById(R.id.bookTopAppBar)
        setSupportActionBar(toolBar)
        this.getWindow().setStatusBarColor(getColor(R.color.colorPrimaryDark))
        avatarImageView = findViewById(R.id.bookAvatarImageView) as ImageView
        bookImageView = findViewById(R.id.bookCoverImageView)
        Picasso.get()
            .load(R.drawable.photo)
            .transform(CropCircleTransformation())
            .into(avatarImageView)
        Picasso.get()
            .load(R.drawable.under_the_dome_cover) //сдесь должен быть url на обложку книги book.bookImgURL
            .placeholder(R.drawable.book_placeholder)
            .into(bookImageView)

        bottomNavigationView = findViewById(R.id.bookButtomAppBur) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.cartItem -> {
                    //Toast.makeText(this, "cartItem", Toast.LENGTH_LONG)
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("menuItemId", R.id.cartItem)
                    startActivity(intent)
                    true
                }
                R.id.catalogItem -> {
                    //Toast.makeText(this, "catalogItem", Toast.LENGTH_LONG)
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("menuItemId",
                        R.id.catalogItem
                    )
                    startActivity(intent)
                    true
                }
                R.id.ordersItem -> {
                    //Toast.makeText(this, "orderItem", Toast.LENGTH_LONG)
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("menuItemId",
                        R.id.ordersItem
                    )
                    startActivity(intent)
                    true
                }
                R.id.profilItem -> {
                    //Toast.makeText(this, "profileItem", Toast.LENGTH_LONG)
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("menuItemId",
                        R.id.profilItem
                    )
                    startActivity(intent)
                    true
                }
                else -> throw Exception()
            }
        }
        BottomNavBar.initialise(bottomNavigationView)

        bookParams = intent.getStringArrayExtra("book") as Array<String>
        /*bookParams = if(savedInstanceState == null){
            intent.getStringArrayExtra("book") as Array<String>
        } else{
            savedInstanceState.getStringArray(BOOK_PARAMS_KEY) as Array<String>
        }*/



        descriptionText = findViewById(R.id.descriptionTextView)
        bookTitleTextView.text = bookParams[1]
        autorTextView.text = bookParams[2]
        priceTextView.text = bookParams[3]
        descriptionText.text = bookParams[19]
        publisherTextView.text = bookParams[9]
        genreTextView.text = bookParams[8]
        dateTextView.text = bookParams[13]

        descriptionText.maxLines = 5
        var expanded: Boolean = false

        expandDescriptionBtn.setOnClickListener {
            if(!expanded){
                descriptionText.maxLines = Int.MAX_VALUE
                expandDescriptionBtn.text = "СВЕРНУТЬ"
            }
            else{
                descriptionText.maxLines = 5
                expandDescriptionBtn.text = "ЧИТАТЬ ПОЛНОСТЬЮ"
            }
            expanded = !expanded
        }

        showAllInfoBtn.setOnClickListener {
            val intent = Intent(this, BookInfoActivity::class.java)
            intent.putExtra("book", bookParams)
            startActivity(intent)
        }

        showReviewBtn.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }

        addRecensionButton.setOnClickListener {
            val intent = Intent(this, WriteReviewActivity::class.java)
            startActivityForResult(intent, 0)

        }


    }

   /* override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArray(BOOK_PARAMS_KEY,bookParams)
    }*/



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            presenter.saveReview()
            Toast.makeText(this, data?.getStringExtra("text"), Toast.LENGTH_LONG ).show()

    }

    override fun updateRating() {
        123
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
