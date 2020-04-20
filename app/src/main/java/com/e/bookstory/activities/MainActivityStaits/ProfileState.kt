package com.e.bookstory.activities.MainActivityStaits

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class ProfileState(): State {

    override fun sortBtnClicked(context: Context) {
        Toast.makeText(context,"sortBtn : profile", Toast.LENGTH_LONG).show()
    }

    override fun filterBtnClicked(context: Context) {
        Toast.makeText(context,"filterBtn : profile", Toast.LENGTH_LONG).show()
    }
}