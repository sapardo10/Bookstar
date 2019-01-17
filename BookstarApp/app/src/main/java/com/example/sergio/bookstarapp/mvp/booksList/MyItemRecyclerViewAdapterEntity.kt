package com.example.sergio.bookstarapp.mvp.booksList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sergio.bookstarapp.R
import com.example.sergio.bookstarapp.api.Model.Book
import com.example.sergio.bookstarapp.mvp.booksList.BooksFragment.OnListFragmentInteractionListener
import com.example.sergio.bookstarapp.mvp.booksList.MyItemRecyclerViewAdapterEntity.ViewHolder
import com.example.sergio.bookstarapp.room.BookEntity
import kotlinx.android.synthetic.main.fragment_books_list_item.view.content
import kotlinx.android.synthetic.main.fragment_books_list_item.view.item_number

class MyItemRecyclerViewAdapterEntity(
  private val mValues: List<BookEntity>,
  private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<ViewHolder>() {

  private val mOnClickListener: View.OnClickListener

  init {
    mOnClickListener = View.OnClickListener { v ->
      val item = v.tag as Book
      mListener?.onListFragmentInteraction(item)
    }
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.fragment_books_list_item, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {
    val item = mValues[position]
    holder.mIdView.text = item?.title
    var author = item.author
    holder.mContentView.text = author

    with(holder.mView) {
      tag = item
      setOnClickListener(mOnClickListener)
    }
  }

  override fun getItemCount(): Int = mValues.size

  inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
    val mIdView: TextView = mView.item_number
    val mContentView: TextView = mView.content

    override fun toString(): String {
      return super.toString() + " '" + mContentView.text + "'"
    }
  }
}
