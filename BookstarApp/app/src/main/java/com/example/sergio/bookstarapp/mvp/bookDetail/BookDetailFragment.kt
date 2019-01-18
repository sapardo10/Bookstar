package com.example.sergio.bookstarapp.mvp.bookDetail

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.sergio.bookstarapp.R
import com.example.sergio.bookstarapp.api.FullModel.FullBook
import com.example.sergio.bookstarapp.api.Model.Book
import com.example.sergio.bookstarapp.room.BookEntity
import com.example.sergio.bookstarapp.utils.PicassoImplementation

class BookDetailFragment : Fragment() {

  private var listener: BookDetailFragmentInteractionListener? = null

  //UI BINDINGS

  @BindView(R.id.book_title) lateinit var bookTitle: TextView
  @BindView(R.id.authors_text) lateinit var authors: TextView
  @BindView(R.id.authors_label) lateinit var authorsLabel: TextView
  @BindView(R.id.book_cover) lateinit var bookCover: ImageView
  @BindView(R.id.ic_favorite) lateinit var favoriteCheckBox: CheckBox
  @BindView(R.id.edition_count) lateinit var editionCount: TextView
  @BindView(R.id.edition_count_label) lateinit var editionCountLabel: TextView
  @BindView(R.id.first_publish) lateinit var firstPublish: TextView
  @BindView(R.id.first_publish_label) lateinit var firstPublishLabel: TextView
  @BindView(R.id.text_placeholder) lateinit var textPlaceholder: TextView
  @BindView(R.id.number_of_pages) lateinit var numberPages: TextView
  @BindView(R.id.number_of_pages_label) lateinit var numberPagesLabel: TextView

  //VARIABLES

  private var book: Book? = null
  private var bookEntity: BookEntity? = null
  private var presenter: BookDetailFragmentPresenter? = null

  //METHODS

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    var view = inflater.inflate(R.layout.fragment_book_detail, container, false)
    ButterKnife.bind(this, view)
    presenter = BookDetailFragmentPresenter(this)
    bindChangeStateListener()
    hideDetails()
    return view
  }

  /**
   * Binds the behaviour of the app when the user presses the favorite check
   */
  private fun bindChangeStateListener() {
    favoriteCheckBox.setOnCheckedChangeListener { _, checked ->
      onFavoritePressed(checked)
    }
  }

  /**
   * It uses one of the methods from the interface depending wheter the book comes
   * from the service or the database
   */
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

  /**
   * Receives a book as parameter and fills the UI with the necessary information
   */
  fun updateDetails(book: Book) {
    this.book = book
    bookTitle.text = book.title
    authors.text = presenter!!.getNameAuthors(book.authorsName)
    favoriteCheckBox.isChecked = false
    editionCount.text = book.editionCount?.toString()
    firstPublish.text = book.firstPublishYear?.toString()
    var imageId = book.coverId
    if (imageId != 0L) {
      var uri = PicassoImplementation.generateFinalUrl(book.coverId.toString(), "M")
      PicassoImplementation.loadImageOnView(context!!, bookCover, uri)
    } else {
      bookCover.setImageResource(R.drawable.book_placeholder_wrapped)
    }
    presenter!!.getBook(book.key)
    showDetails()
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
    } else {
      bookCover.setImageResource(R.drawable.book_placeholder_wrapped)
    }
    presenter!!.getBook("OLID" + book.key)
    showDetails()
  }

  /**
   * It fills out more details when the api responds with more information about the book
   */
  fun addDetailsFromApi(book: FullBook) {
    numberPages.text = book.numberOfPages.toString()
    numberPages.visibility = View.VISIBLE
    numberPagesLabel.visibility = View.VISIBLE
  }

  /**
   * Shows all the UI elements when a book is available to show information
   */
  private fun showDetails() {
    bookTitle.visibility = View.VISIBLE
    authors.visibility = View.VISIBLE
    authorsLabel.visibility = View.VISIBLE
    bookCover.visibility = View.VISIBLE
    favoriteCheckBox.visibility = View.VISIBLE
    editionCount.visibility = View.VISIBLE
    editionCountLabel.visibility = View.VISIBLE
    firstPublish.visibility = View.VISIBLE
    firstPublishLabel.visibility = View.VISIBLE
    textPlaceholder.visibility = View.GONE
  }

  /**
   * Hides all the UI fields and leaves a placeholder to indicate the user to select a book
   */
  private fun hideDetails() {
    bookTitle.visibility = View.GONE
    authors.visibility = View.GONE
    authorsLabel.visibility = View.GONE
    bookCover.visibility = View.GONE
    favoriteCheckBox.visibility = View.GONE
    editionCount.visibility = View.GONE
    editionCountLabel.visibility = View.GONE
    firstPublish.visibility = View.GONE
    firstPublishLabel.visibility = View.GONE
    numberPages.visibility = View.GONE
    numberPagesLabel.visibility = View.GONE
    textPlaceholder.visibility = View.VISIBLE
  }

  fun showErrorToast(error: String?) {
    Log.d("ERROR", "Couldn't reach the server $error")
  }

  /**
   * Interface to handle the interactions on this fragment
   */
  interface BookDetailFragmentInteractionListener {

    /**
     * Method that should implement what to do when the user clicks on the favorite check
     * @param book book that the user is seeing
     * @param isFavorite true if the user wish to add it to favorites, false if the user wants
     * to remove it
     */
    fun onFavoritePressedFragmentInteraction(
      book: Book,
      isFavorite: Boolean
    )

    //Same method but for books stored on the database
    fun onFavoriteEntityPressedFragmentInteraction(
      book: BookEntity,
      isFavorite: Boolean
    )
  }

}
