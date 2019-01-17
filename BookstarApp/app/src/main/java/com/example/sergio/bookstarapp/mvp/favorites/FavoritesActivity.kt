package com.example.sergio.bookstarapp.mvp.favorites

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.sergio.bookstarapp.R
import com.example.sergio.bookstarapp.api.Model.Book
import com.example.sergio.bookstarapp.mvp.bookDetail.BookDetailActivity
import com.example.sergio.bookstarapp.mvp.bookDetail.BookDetailFragment
import com.example.sergio.bookstarapp.mvp.bookDetail.BookDetailFragment.BookDetailFragmentInteractionListener
import com.example.sergio.bookstarapp.mvp.booksList.BooksFragment
import com.example.sergio.bookstarapp.mvp.booksList.BooksFragment.OnListFragmentInteractionListener
import com.example.sergio.bookstarapp.room.BookEntity
import com.google.gson.Gson

class FavoritesActivity : AppCompatActivity(), FavoritesView, OnListFragmentInteractionListener,
    BookDetailFragmentInteractionListener {

  //UI BINDINGS

  private var booksListFragment: BooksFragment? = null
  private var bookDetailFragment: BookDetailFragment? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_favorites)
    booksListFragment = supportFragmentManager.findFragmentById(R.id.books_list) as BooksFragment?
    bookDetailFragment =
        supportFragmentManager.findFragmentById(R.id.book_detail) as BookDetailFragment?
  }

  override fun updateBooksList(books: List<BookEntity>) {
    booksListFragment!!.updateListEntity(books)
  }

  override fun onListFragmentInteraction(item: Book) {
    if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      bookDetailFragment!!.updateDetails(item)
    } else {
      val gson = Gson()
      val intent = Intent(this, BookDetailActivity::class.java)
      intent.putExtra("book", gson.toJson(item))
      startActivity(intent)

    }
    Toast.makeText(this, "item clicked $title", Toast.LENGTH_SHORT)
        .show()
  }

  override fun onFavoritePressedFragmentInteraction(
    book: Book,
    isFavorite: Boolean
  ) {
  }

}
