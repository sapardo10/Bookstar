package com.example.sergio.bookstarapp.mvp.booksList

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sergio.bookstarapp.R
import com.example.sergio.bookstarapp.api.Model.Book
import com.example.sergio.bookstarapp.room.BookEntity

class BooksFragment : Fragment() {

  private var columnCount = 1

  private var listener: OnListFragmentInteractionListener? = null

  private var view: RecyclerView? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    view = inflater.inflate(R.layout.fragment_books_list, container, false) as RecyclerView?

    if (view is RecyclerView) {
      with(view!!) {
        layoutManager = when {
          columnCount <= 1 -> LinearLayoutManager(context)
          else -> GridLayoutManager(context, columnCount)
        }
        var emptyList = mutableListOf<Book>()
        adapter = MyItemRecyclerViewAdapter(
            emptyList, listener
        )
      }
    }
    return view
  }

  fun updateList(booksList: List<Book>) {
    view?.adapter =
        MyItemRecyclerViewAdapter(booksList, listener)

  }

  fun updateListEntity(booksList: List<BookEntity>) {
    view?.adapter =
        MyItemRecyclerViewAdapterEntity(booksList, listener)

  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if (context is OnListFragmentInteractionListener) {
      listener = context
    } else {
      throw RuntimeException(
          context.toString() + " must implement OnListFragmentInteractionListener"
      )
    }
  }

  override fun onDetach() {
    super.onDetach()
    listener = null
  }

  interface OnListFragmentInteractionListener {
    /**
     * It handles what the app should do when the user clicks a book from any of the list on the app
     */
    fun onListFragmentInteraction(item: Book)

    //It does the same but on the favorites screen where the books comes from the database
    fun onListFragmentInteraction(item: BookEntity)
  }

}
