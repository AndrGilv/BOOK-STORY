package com.e.bookstory.presenters

import android.content.Context
import com.e.bookstory.model.AppModel
import com.e.bookstory.model.AppModelInterface
import com.e.bookstory.activities.views.ReviewActivityView
import com.e.bookstory.entities.Review
import com.e.bookstory.observers.ReviewObserver
import java.util.ArrayList

class ReviewActivityPresenter(context: Context): Presenter(context), ReviewObserver {

    private var reviewActivityView: ReviewActivityView? = null

    val reviews: ArrayList<Review>
        get() {return model.getReviews()}

    var loading: Boolean = false

    init {
        model.registerReviewObservers(this)
    }

    fun attachView(reviewActivityView: ReviewActivityView){
        this.reviewActivityView = reviewActivityView;
    }

    fun detachView(){
        reviewActivityView = null
    }

    fun getNextReviewPortion(){
        loading = true

        //reviews.addAll(model.loadNextReviewsPortion())
        //update()
        model.loadNextReviewsPortion()
        loading = false
    }

    override fun update() {
        reviewActivityView?.notifyRecyclerView()
    }

}