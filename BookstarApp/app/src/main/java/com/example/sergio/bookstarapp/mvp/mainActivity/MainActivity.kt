package com.example.sergio.bookstarapp.mvp.mainActivity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
import com.example.sergio.bookstarapp.mvp.mainActivity.SearchBarFragment.SearchBarFragmentInteractionListener
import com.google.gson.Gson

class MainActivity : AppCompatActivity(),
    MainActivityView,
    OnListFragmentInteractionListener,
    SearchBarFragmentInteractionListener,
    BookDetailFragmentInteractionListener {

  private lateinit var presenter: MainPresenter

  //UI BINDINGS

  private var booksListFragment: BooksFragment? = null
  private var bookDetailFragment: BookDetailFragment? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
    presenter = MainPresenter(this)
    ButterKnife.bind(this)
    booksListFragment = supportFragmentManager.findFragmentById(R.id.books_list) as BooksFragment?
    bookDetailFragment =
        supportFragmentManager.findFragmentById(R.id.book_detail) as BookDetailFragment?
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
      val gson = Gson()
      val intent = Intent(this, BookDetailActivity::class.java)
      intent.putExtra("book", gson.toJson(item))
      startActivity(intent)

    }
    Toast.makeText(this, "ApiResponseSearch item clicked $title", Toast.LENGTH_SHORT)
        .show()
  }

  //BOOK DETAIL BEHAVIOUR

  override fun onFavoritePressedFragmentInteraction() {
    Toast.makeText(this, "Favorite pressed", Toast.LENGTH_SHORT)
        .show()
  }
}
