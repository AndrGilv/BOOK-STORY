package com.e.bookstory.activities.views

import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.View
import com.e.bookstory.entities.Book
import com.e.bookstory.entities.Purchase
import java.util.ArrayList

interface BookActivityView: View {

    fun updateRating()

}