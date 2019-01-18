package com.example.sergio.bookstarapp.mvp.booksList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.sergio.bookstarapp.R
import com.example.sergio.bookstarapp.api.Model.Book
import com.example.sergio.bookstarapp.mvp.booksList.BooksFragment.OnListFragmentInteractionListener
import com.example.sergio.bookstarapp.mvp.booksList.MyItemRecyclerViewAdapter.ViewHolder
import com.example.sergio.bookstarapp.utils.PicassoImplementation
import kotlinx.android.synthetic.main.fragment_books_list_item.view.author
import kotlinx.android.synthetic.main.fragment_books_list_item.view.book_cover
import kotlinx.android.synthetic.main.fragment_books_list_item.view.book_title

class MyItemRecyclerViewAdapter(
  private val mValues: List<Book>,
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
    var author = ""
    if (item?.authorsName != null) {
      author = item?.authorsName[0]
    }
    holder.mContentView.text = author
    var imageId = item.coverId
    if (imageId != 0L) {
      var uri = PicassoImplementation.generateFinalUrl(item.coverId.toString(), "S")
      PicassoImplementation.loadImageOnView(holder.mView.context, holder.mCoverView, uri)
    }
    with(holder.mView) {
      tag = item
      setOnClickListener(mOnClickListener)
    }
  }

  override fun getItemCount(): Int = mValues.size

  inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
    val mIdView: TextView = mView.book_title
    val mContentView: TextView = mView.author
    val mCoverView: ImageView = mView.book_cover

    override fun toString(): String {
      return super.toString() + " '" + mContentView.text + "'"
    }
  }
}
