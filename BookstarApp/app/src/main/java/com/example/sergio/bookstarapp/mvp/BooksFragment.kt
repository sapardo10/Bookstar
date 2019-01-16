package com.example.sergio.bookstarapp.mvp

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

import com.example.sergio.bookstarapp.mvp.dummy.DummyContent
import com.example.sergio.bookstarapp.mvp.dummy.DummyContent.DummyItem

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [BooksFragment.OnListFragmentInteractionListener] interface.
 */
class BooksFragment : Fragment() {

  private var columnCount = 1

  private var listener: OnListFragmentInteractionListener? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_books_list, container, false)

    // Set the adapter
    if (view is RecyclerView) {
      with(view) {
        layoutManager = when {
          columnCount <= 1 -> LinearLayoutManager(context)
          else -> GridLayoutManager(context, columnCount)
        }
        adapter = MyItemRecyclerViewAdapter(DummyContent.ITEMS, listener)
      }
    }
    return view
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
    fun onListFragmentInteraction(item: DummyItem?)
  }

}
