package com.e.bookstory.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.activityAdapters.BookInfoRVAdapter
import com.e.bookstory.BottomNavBar
import com.e.bookstory.R
import com.e.bookstory.entities.Book
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.lang.Exception

class BookInfoActivity : AppCompatActivity() {

    private lateinit var book: Book
    private val BOOK_KEY: String = "book"

    private lateinit var rv: RecyclerView
    private lateinit var avatarImageView: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_info)

        val toolBar: Toolbar = findViewById(R.id.allBookInfoTopAppBar)
        setSupportActionBar(toolBar)
        this.getWindow().setStatusBarColor(getColor(R.color.colorPrimaryDark))
        avatarImageView = findViewById(R.id.bookInfoAvatarImageView) as ImageView
        Picasso.get()
            .load(R.drawable.photo)
            .transform(CropCircleTransformation())
            .into(avatarImageView)

        bottomNavigationView = findViewById(R.id.bookInfoButtomAppBur) as BottomNavigationView
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

        book = if(savedInstanceState != null){
            Book.bildBookByParams(savedInstanceState.getStringArray(BOOK_KEY) as Array<String>)
        }else{
            Book.bildBookByParams(intent.getStringArrayExtra("book"))
        }

        rv = findViewById(R.id.bookInfoRView)
        val llm = LinearLayoutManager(this)
        rv.layoutManager = llm
        val adapter: BookInfoRVAdapter =
            BookInfoRVAdapter(book, this)
        rv.adapter = adapter


    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = -1;
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArray(BOOK_KEY,book.getParams())
    }
}
