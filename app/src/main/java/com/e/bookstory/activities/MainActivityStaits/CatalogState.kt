package com.e.bookstory.activities.MainActivityStaits

import android.content.Context
import android.widget.Toast

class CatalogState: State {
    override fun sortBtnClicked(context: Context) {
        Toast.makeText(context,"sortBtn : catalog",Toast.LENGTH_LONG).show()
    }

    override fun filterBtnClicked(context: Context) {
        Toast.makeText(context,"filterBtn : catalog", Toast.LENGTH_LONG).show()
    }
}