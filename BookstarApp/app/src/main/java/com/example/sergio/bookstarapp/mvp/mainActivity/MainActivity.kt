package com.example.sergio.bookstarapp.mvp.mainActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import butterknife.ButterKnife
import com.example.sergio.bookstarapp.R.layout
import com.example.sergio.bookstarapp.mvp.BooksFragment.OnListFragmentInteractionListener
import com.example.sergio.bookstarapp.mvp.bookDetail.BookDetailFragment.BookDetailFragmentInteractionListener
import com.example.sergio.bookstarapp.mvp.dummy.DummyContent.DummyItem
import com.example.sergio.bookstarapp.mvp.mainActivity.SearchBarFragment.SearchBarFragmentInteractionListener

class MainActivity : AppCompatActivity(),
    MainActivityView,
    OnListFragmentInteractionListener,
    SearchBarFragmentInteractionListener,
    BookDetailFragmentInteractionListener {

  //UI BINDINGS

  private lateinit var presenter: MainPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
    presenter = MainPresenter(this)
    ButterKnife.bind(this)
  }

  //SEARCH BAR BEHAVIOUR

  override fun onSearchFragmentInteraction(searchText: String) {
    Toast.makeText(this, "Search bar pressed with $searchText", Toast.LENGTH_SHORT)
        .show()
  }

  //BOOK LIST BEHAVIOUR

  override fun onListFragmentInteraction(item: DummyItem?) {
    Toast.makeText(this, "Book item clicked", Toast.LENGTH_SHORT).show()
  }

  //BOOK DETAIL BEHAVIOUR

  override fun onFavoritePressedFragmentInteraction() {
    Toast.makeText(this, "Favorite pressed", Toast.LENGTH_SHORT).show()
  }
}
