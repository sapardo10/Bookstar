package com.example.sergio.bookstarapp.mvp.bookDetail

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.sergio.bookstarapp.R
import com.example.sergio.bookstarapp.api.Model.Book

class BookDetailFragment : Fragment() {

  private var listener: BookDetailFragmentInteractionListener? = null

  //UI BINDINGS

  @BindView(R.id.book_title) lateinit var bookTitle: TextView
  @BindView(R.id.authors_text) lateinit var authors: TextView
  @BindView(R.id.book_cover) lateinit var bookCover: ImageView

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    var view = inflater.inflate(R.layout.fragment_book_detail, container, false)
    ButterKnife.bind(this, view)
    return view
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

  fun updateDetails(book: Book) {
    bookTitle.text = book.title
    authors.text = book.authorsName[0]
  }

  interface BookDetailFragmentInteractionListener {
    fun onFavoritePressedFragmentInteraction()
  }

}
