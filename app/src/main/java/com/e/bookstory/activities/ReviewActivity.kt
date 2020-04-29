package com.e.bookstory.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.BottomNavBar
import com.e.bookstory.R
import com.e.bookstory.activities.views.ReviewActivityView
import com.e.bookstory.activityAdapters.ReviewRVAdapter
import com.e.bookstory.entities.Review
import com.e.bookstory.presenters.CartFragmentPresenter
import com.e.bookstory.presenters.ReviewActivityPresenter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.lang.Exception
import java.util.*

class ReviewActivity : AppCompatActivity(), ReviewActivityView {

    private lateinit var rv: RecyclerView
    private lateinit var avatarImageView: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var presenter: ReviewActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        presenter = ReviewActivityPresenter(this)
        presenter.attachView(this)

        if(savedInstanceState != null){

        }

        val toolBar: Toolbar = findViewById(R.id.reviewTopAppBar)
        setSupportActionBar(toolBar)
        this.getWindow().setStatusBarColor(getColor(R.color.colorPrimaryDark))
        avatarImageView = findViewById(R.id.reviewAvatarImageView) as ImageView
        Picasso.get()
            .load(R.drawable.photo)
            .transform(CropCircleTransformation())
            .into(avatarImageView)

        bottomNavigationView = findViewById(R.id.reviewButtomAppBur) as BottomNavigationView
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


        rv = findViewById(R.id.reviewRView)
        val llm = LinearLayoutManager(this)
        rv.layoutManager = llm
        val adapter: ReviewRVAdapter =
            ReviewRVAdapter(presenter.reviews, this)
        rv.setAdapter(adapter)

        presenter.getNextReviewPortion()
        rv.addOnScrollListener(object: RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy > 0) //check for scroll down
                {
                    val totalItemCount = llm.itemCount
                    val lastVisibleItem = llm.findLastVisibleItemPosition()
                    if(!presenter.loading){
                        if((lastVisibleItem)  >= totalItemCount - 2){
                            presenter.getNextReviewPortion()
                        }
                    }
                }

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun notifyRecyclerView() {
        rv.adapter?.notifyDataSetChanged()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }
}
