package com.e.bookstory.activityAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.R
import com.e.bookstory.entities.Review


class ReviewRVAdapter(reviewList: List<Review>, context: Context): RecyclerView.Adapter<ReviewRVAdapter.ReviewViewHolder>(){
    var reviewList: List<Review>
    var context: Context

    init {
        this.reviewList = reviewList
        this.context = context
    }

    class ReviewViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        var reviewerName: TextView
        var reviewDate: TextView
        var reviewRating: TextView
        var reviewText: TextView
        var expandBtn: Button

        init {
            reviewerName = itemView.findViewById(R.id.reviewerNameTV)
            reviewDate = itemView.findViewById(R.id.reviewDateTV)
            reviewRating = itemView.findViewById(R.id.reviewRatingTV)
            reviewText = itemView.findViewById(R.id.reviewTextTV)
            expandBtn = itemView.findViewById(R.id.expandReviewBtn)

            //reviewText.setLines((if(reviewText.lineCount > 3) 3 else reviewText.lineCount))
            reviewText.maxLines = 3
            var expanded: Boolean = false
            expandBtn.setOnClickListener {
                if(!expanded){
                    //reviewText.setLines(reviewText.lineCount)
                    reviewText.maxLines = Int.MAX_VALUE
                    expandBtn.text = "СВЕРНУТЬ"
                }
                else{
                    //reviewText.setLines((if(reviewText.lineCount > 3) 3 else reviewText.lineCount))
                    reviewText.maxLines = 3
                    expandBtn.text = "ЧИТАТЬ ПОЛНОСТЬЮ"
                }
                expanded = !expanded
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.review_layout,parent, false)
        val pvh: ReviewViewHolder =
            ReviewViewHolder(
                v,
                context
            )
        return pvh;
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.reviewDate.text = reviewList[position].date.toString()
        holder.reviewRating.text = (reviewList[position].rating.toString() + " из 10")
        holder.reviewText.text = reviewList[position].text
        holder.reviewerName.text = reviewList[position].author
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }


}