package com.e.bookstory.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.e.bookstory.R
import kotlinx.android.synthetic.main.activity_write_review.*


class WriteReviewActivity : AppCompatActivity() {
    private val RATING_KEY: String = "rating"
    private val REVIEW_KEY: String = "review"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_review)

        if(savedInstanceState != null){
            reviewEditText.text = savedInstanceState.getString(REVIEW_KEY) as Editable
            reviewRatingBar.rating = savedInstanceState.getFloat(RATING_KEY)
        }

        saveReviewBtn.setOnClickListener {
            val intent = Intent()
            val str: String = reviewEditText.text.toString()
            intent.putExtra("text", str)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        reviewRatingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser -> run{
            Toast.makeText(this, rating.toString(), Toast.LENGTH_LONG).show()
        } }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(REVIEW_KEY,reviewEditText.text.toString())
        outState.putString(RATING_KEY,reviewRatingBar.rating.toString())
    }
}
