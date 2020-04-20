package com.e.bookstory.activities.MainActivityStaits

import android.content.Context
import android.widget.Toast

class CartState: State {
    override fun sortBtnClicked(context: Context) {
        Toast.makeText(context,"sortBtn : cart", Toast.LENGTH_LONG).show()
    }

    override fun filterBtnClicked(context: Context) {
        Toast.makeText(context,"filterBtn : cart", Toast.LENGTH_LONG).show()
    }
}