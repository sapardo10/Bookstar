package com.example.sergio.bookstarapp.mvp.bookDetail

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.sergio.bookstarapp.R
import com.example.sergio.bookstarapp.api.Model.Book
import com.example.sergio.bookstarapp.room.BookEntity
import com.example.sergio.bookstarapp.utils.PicassoImplementation

class BookDetailFragment : Fragment() {

  private var listener: BookDetailFragmentInteractionListener? = null

  //UI BINDINGS

  @BindView(R.id.book_title) lateinit var bookTitle: TextView
  @BindView(R.id.authors_text) lateinit var authors: TextView
  @BindView(R.id.book_cover) lateinit var bookCover: ImageView
  @BindView(R.id.ic_favorite) lateinit var favoriteCheckBox: CheckBox
  @BindView(R.id.edition_count) lateinit var editionCount: TextView
  @BindView(R.id.first_publish) lateinit var firstPublish: TextView

  //VARIABLES

  private var book: Book? = null
  private var bookEntity: BookEntity? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    var view = inflater.inflate(R.layout.fragment_book_detail, container, false)
    ButterKnife.bind(this, view)
    bindChangeStateListener()
    return view
  }

  private fun bindChangeStateListener() {
    favoriteCheckBox.setOnCheckedChangeListener { _, checked ->
      onFavoritePressed(checked)
    }
  }

  private fun onFavoritePressed(isFavorite: Boolean) {
    if (book != null)
      listener?.onFavoritePressedFragmentInteraction(book!!, isFavorite)
    else if (bookEntity != null) {
      listener?.onFavoriteEntityPressedFragmentInteraction(bookEntity!!, isFavorite)
    }
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
    this.book = book
    bookTitle.text = book.title
    authors.text = getNameAuthors(book.authorsName)
    favoriteCheckBox.isChecked = false
    editionCount.text = book.editionCount?.toString()
    firstPublish.text = book.firstPublishYear?.toString()
    var imageId = book.coverId
    if (imageId != 0L) {
      var uri = PicassoImplementation.generateFinalUrl(book.coverId.toString(), "M")
      PicassoImplementation.loadImageOnView(context!!, bookCover, uri)
    }
  }

  fun updateDetails(book: BookEntity) {
    this.bookEntity = book
    bookTitle.text = book.title
    authors.text = book.author
    favoriteCheckBox.isChecked = book.isFavorite
    editionCount.text = book.editionCount?.toString()
    firstPublish.text = book.firstPublishYear?.toString()
    var imageId = book.coverId
    if (imageId != 0L) {
      var uri = PicassoImplementation.generateFinalUrl(book.coverId.toString(), "M")
      PicassoImplementation.loadImageOnView(context!!, bookCover, uri)
    }
  }

  private fun getNameAuthors(authorsName: Array<String>): String {
    var res = "No authors"
    if (authorsName != null) {
      res = ""
      for (author in authorsName) {
        res += "$author ,"
      }
    }
    return res
  }

  interface BookDetailFragmentInteractionListener {
    fun onFavoritePressedFragmentInteraction(
      book: Book,
      isFavorite: Boolean
    )

    fun onFavoriteEntityPressedFragmentInteraction(
      book: BookEntity,
      isFavorite: Boolean
    )
  }

}
