package com.example.sergio.bookstarapp.mvp.bookDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.sergio.bookstarapp.R
import com.example.sergio.bookstarapp.api.Model.Book
import com.example.sergio.bookstarapp.mvp.bookDetail.BookDetailFragment.BookDetailFragmentInteractionListener
import com.google.gson.Gson

class BookDetailActivity : AppCompatActivity(), BookDetailFragmentInteractionListener,
    BookDetailView {

  //UI BINDINGS

  private var bookDetailFragment: BookDetailFragment? = null

  //VARIABLES

  private lateinit var presenter: BookDetailPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_book_detail)
    presenter = BookDetailPresenter(this)
    val book = deserializeObjectFromGson(intent.getStringExtra("book"))
    bindFragments()
    bookDetailFragment!!.updateDetails(book)
  }

  /**
   * Connects the fragments on the UI to the variables inside the activity for further manipulation
   */
  private fun bindFragments() {
    bookDetailFragment =
        supportFragmentManager.findFragmentById(R.id.book_detail) as BookDetailFragment?
  }

  /**
   * It takes a string containing a json and it deserializes it to a Book Object
   */
  private fun deserializeObjectFromGson(json: String): Book {
    val gson = Gson()
    return gson.fromJson<Book>(json, Book::class.java)
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
}
