package com.example.sergio.bookstarapp.mvp.bookDetail

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sergio.bookstarapp.R

class BookDetailFragment : Fragment() {
  private var listener: BookDetailFragmentInteractionListener? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_book_detail, container, false)
  }

  fun onFavoritePressed() {
    listener?.onFavoritePressedFragmentInteraction()
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if (context is BookDetailFragmentInteractionListener) {
      listener = context
    } else {
      throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
    }
  }

  override fun onDetach() {
    super.onDetach()
    listener = null
  }

  interface BookDetailFragmentInteractionListener {
    fun onFavoritePressedFragmentInteraction()
  }

}
