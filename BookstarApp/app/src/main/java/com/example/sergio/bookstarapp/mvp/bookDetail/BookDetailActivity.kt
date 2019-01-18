package com.example.sergio.bookstarapp.mvp.bookDetail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.sergio.bookstarapp.R
import com.example.sergio.bookstarapp.api.Model.Book
import com.example.sergio.bookstarapp.mvp.bookDetail.BookDetailFragment.BookDetailFragmentInteractionListener
import com.example.sergio.bookstarapp.room.BookEntity
import com.google.gson.Gson

class BookDetailActivity : AppCompatActivity(), BookDetailFragmentInteractionListener,
    BookDetailView {

  //CONSTANTS
  companion object {
    /**Tag to save and take the book from the intent when starting this activity*/
    const val INTENT_BOOK = "book"
    /**If true, the object inside the intent is a BookEntity. Its a Book otherwise */
    const val IS_ENTITY = "is_entity"
  }

  //UI BINDINGS

  private var bookDetailFragment: BookDetailFragment? = null

  //VARIABLES

  private lateinit var presenter: BookDetailPresenter

  //METHODS

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_book_detail)
    presenter = BookDetailPresenter(this)
    val isEntity = intent.getBooleanExtra(IS_ENTITY, false)
    bindFragments()
    initializeBookDetails(intent, isEntity)
  }

  /**
   * Connects the fragments on the UI to the variables inside the activity for further manipulation
   */
  private fun bindFragments() {
    bookDetailFragment =
        supportFragmentManager.findFragmentById(R.id.book_detail) as BookDetailFragment?
  }

  /**
   * It diffirientiates between the two type of data and shows the details on the fragment
   * depending on the book passed by intent
   */
  private fun initializeBookDetails(
    intent: Intent,
    isEntity: Boolean
  ) {
    if (!isEntity) {
      var book: Book = deserializeBookObjectFromGson(intent.getStringExtra(INTENT_BOOK))
      bookDetailFragment!!.updateDetails(book)
    } else {
      var book: BookEntity = deserializeBookEntityObjectFromGson(intent.getStringExtra(INTENT_BOOK))
      bookDetailFragment!!.updateDetails(book)
    }
  }

  /**
   * It takes a string containing a json and it deserializes it to a Book Object
   */
  private fun deserializeBookObjectFromGson(json: String): Book {
    val gson = Gson()
    return gson.fromJson<Book>(json, Book::class.java)
  }

  /**
   * It takes a string containing a json and it deserializes it to a BookEntity Object
   */
  private fun deserializeBookEntityObjectFromGson(json: String): BookEntity {
    val gson = Gson()
    return gson.fromJson<BookEntity>(json, BookEntity::class.java)
  }

  //BOOK DETAIL BEHAVIOUR

  override fun onFavoritePressedFragmentInteraction(
    book: Book,
    isFavorite: Boolean
  ) {
    presenter.saveFavorite(book, isFavorite)
    var title = book.title
    if (isFavorite) {
      Toast.makeText(this, "Saved $title in favorites", Toast.LENGTH_SHORT)
          .show()
    }
  }

  override fun onFavoriteEntityPressedFragmentInteraction(
    book: BookEntity,
    isFavorite: Boolean
  ) {
    presenter.saveFavorite(book, isFavorite)
    var title = book.title
    if (isFavorite) {
      Toast.makeText(this, "Saved $title in favorites", Toast.LENGTH_SHORT)
          .show()
    }
  }
}
