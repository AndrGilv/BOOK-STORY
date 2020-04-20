package com.e.bookstory.activityAdapters

import android.app.ActionBar
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.bookstory.R
import com.e.bookstory.entities.Order

class OrdersRVAdapter(ordersList: List<Order>, context: Context ): RecyclerView.Adapter<OrdersRVAdapter.OrderViewHolder>(){
    var ordersList: List<Order>
    var context: Context

    init {
        this.ordersList = ordersList
        this.context = context

    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cv: CardView
        var date: TextView
        var oderSum: TextView
        var amountOfBooks: TextView
        var listOfBooks: RecyclerView
        var listLayout: ConstraintLayout
        var expandBtn: ImageButton
        var expandLessBtn: ImageButton

        init {
            cv = itemView.findViewById(R.id.orderCard)
            date = itemView.findViewById(R.id.orderDate)
            oderSum = itemView.findViewById(R.id.costTView)
            amountOfBooks = itemView.findViewById(R.id.bookAmountTView)
            listOfBooks = itemView.findViewById(R.id.booksInOrderListView)
            listLayout = itemView.findViewById(R.id.bookListLayout)
            expandBtn = itemView.findViewById(R.id.expandBtn)
            expandLessBtn = itemView.findViewById(R.id.expandLessBtn)

            expandBtn.setOnClickListener {
                listLayout.layoutParams.height = ActionBar.LayoutParams.WRAP_CONTENT
                expandBtn.visibility = View.GONE
                expandLessBtn.visibility = View.VISIBLE

            }
            expandLessBtn.setOnClickListener {
                listLayout.layoutParams.height = 0
                expandLessBtn.visibility = View.GONE
                expandBtn.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.order_card_layout,parent, false)
        val pvh: OrderViewHolder =
            OrderViewHolder(v)
        return pvh;
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.date.text = ordersList[position].date.toString()
        holder.oderSum.text = "недорого"
        holder.amountOfBooks.text = ordersList[position].books.size.toString()
        val recyclerView = holder.listOfBooks
        val llm = LinearLayoutManager(context)
        recyclerView.layoutManager = llm
        val adapter: BooksInOrderRVAdapter =
            BooksInOrderRVAdapter(
                ordersList[position].books,
                context
            )
        recyclerView.adapter = adapter
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }
}