package com.example.sergio.bookstarapp.mvp.mainActivity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import butterknife.ButterKnife
import com.example.sergio.bookstarapp.R
import com.example.sergio.bookstarapp.R.layout
import com.example.sergio.bookstarapp.api.Model.Book
import com.example.sergio.bookstarapp.mvp.bookDetail.BookDetailActivity
import com.example.sergio.bookstarapp.mvp.bookDetail.BookDetailFragment
import com.example.sergio.bookstarapp.mvp.bookDetail.BookDetailFragment.BookDetailFragmentInteractionListener
import com.example.sergio.bookstarapp.mvp.booksList.BooksFragment
import com.example.sergio.bookstarapp.mvp.booksList.BooksFragment.OnListFragmentInteractionListener
import com.example.sergio.bookstarapp.mvp.favorites.FavoritesActivity
import com.example.sergio.bookstarapp.mvp.mainActivity.SearchBarFragment.SearchBarFragmentInteractionListener
import com.example.sergio.bookstarapp.room.BookEntity
import com.google.gson.Gson

class MainActivity : AppCompatActivity(),
    MainActivityView,
    OnListFragmentInteractionListener,
    SearchBarFragmentInteractionListener,
    BookDetailFragmentInteractionListener {
  override fun onListFragmentInteraction(item: BookEntity) {
    TODO(
        "not implemented"
    ) //To change body of created functions use File | Settings | File Templates.
  }

  //UI BINDINGS

  private var booksListFragment: BooksFragment? = null
  private var bookDetailFragment: BookDetailFragment? = null

  //VARIABLES

  private lateinit var presenter: MainPresenter

  //METHODS

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
    initializePresenter()
    ButterKnife.bind(this)
    bindFragments()
  }

  private fun initializePresenter() {
    presenter = MainPresenter(this)
  }

  /**
   * Connects the fragments on the UI to the variables inside the activity for further manipulation
   */
  private fun bindFragments() {
    booksListFragment = supportFragmentManager.findFragmentById(R.id.books_list) as BooksFragment?
    bookDetailFragment =
        supportFragmentManager.findFragmentById(R.id.book_detail) as BookDetailFragment?
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.main_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle item selection
    return when (item.itemId) {
      R.id.action_favorite -> {
        openFavoritesActivity()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  /**
   * Open the Favorites screen
   */
  private fun openFavoritesActivity() {
    val intent = Intent(this, FavoritesActivity::class.java)
    startActivity(intent)
  }

  /**
   * If necessary for being in portrait mode it opens the BookDetails screen
   * @param item Book object to fill the fields of the screen
   */
  private fun openBookDetailActivity(item: Book) {
    val gson = Gson()
    val intent = Intent(this, BookDetailActivity::class.java)
    intent.putExtra("book", gson.toJson(item))
    startActivity(intent)
  }

  override fun updateBooksList(books: List<Book>) {
    booksListFragment!!.updateList(books)
    Toast.makeText(this, "result update with", Toast.LENGTH_SHORT)
        .show()
  }

  override fun showErrorToast(error: String?) {
    Toast.makeText(this, "Couldn't reach the server, $error", Toast.LENGTH_SHORT)
        .show()
  }

  //SEARCH BAR BEHAVIOUR

  override fun onSearchFragmentInteraction(searchText: String) {
    presenter.searchBooks(searchText)
    Toast.makeText(this, "Search bar pressed with $searchText", Toast.LENGTH_SHORT)
        .show()
  }

  //BOOK LIST BEHAVIOUR

  override fun onListFragmentInteraction(item: Book) {
    var title = item?.title
    if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      bookDetailFragment!!.updateDetails(item)
    } else {
      openBookDetailActivity(item)
    }
    Toast.makeText(this, "ApiResponseSearch item clicked $title", Toast.LENGTH_SHORT)
        .show()
  }

  //BOOK DETAIL BEHAVIOUR

  override fun onFavoritePressedFragmentInteraction(
    book: Book,
    isFavorite: Boolean
  ) {
    presenter.saveFavorite(book, isFavorite)
    var title = book.title
    Toast.makeText(this, "Favorite pressed with book: $title and $isFavorite", Toast.LENGTH_SHORT)
        .show()
  }
}
