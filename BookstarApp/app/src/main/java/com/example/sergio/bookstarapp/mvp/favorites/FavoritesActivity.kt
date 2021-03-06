package com.example.sergio.bookstarapp.mvp.favorites

import android.arch.lifecycle.Observer
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
  private var books: List<BookEntity> = emptyList()

  //VARIABLES

  private var presenter: FavoritesPresenter? = null

  //METHODS

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_favorites)
    presenter = FavoritesPresenter(this)
    booksListFragment = supportFragmentManager.findFragmentById(R.id.books_list) as BooksFragment?
    bookDetailFragment =
        supportFragmentManager.findFragmentById(R.id.book_detail) as BookDetailFragment?
    presenter!!.allFavorites.observe(this, Observer<List<BookEntity>> { t ->
      if (t != null) {
        updateBooksList(t)
      }
    })
  }

  override fun updateBooksList(books: List<BookEntity>) {
    booksListFragment?.updateListEntity(books)
  }

  override fun onListFragmentInteraction(item: BookEntity) {
    if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      bookDetailFragment?.updateDetails(item)
    } else {
      val gson = Gson()
      val intent = Intent(this, BookDetailActivity::class.java)
      intent.putExtra(BookDetailActivity.INTENT_BOOK, gson.toJson(item))
      intent.putExtra(BookDetailActivity.IS_ENTITY, true)
      startActivity(intent)

    }
  }

  //BOOK LIST BEHAVIOUR

  override fun onListFragmentInteraction(item: Book) {
  }

  //BOOK DETAIL BEHAVIOUR

  //Empty because it will never receive a book from service
  override fun onFavoritePressedFragmentInteraction(
    book: Book,
    isFavorite: Boolean
  ) {
  }

  override fun onFavoriteEntityPressedFragmentInteraction(
    book: BookEntity,
    isFavorite: Boolean
  ) {
    presenter!!.saveFavorite(book, isFavorite)
    var title = book.title
    if (isFavorite) {
      Toast.makeText(this, "Saved $title in Favorites", Toast.LENGTH_SHORT)
          .show()
    } else {
      Toast.makeText(this, "Removed $title from Favorites", Toast.LENGTH_SHORT)
          .show()
    }
  }

}
