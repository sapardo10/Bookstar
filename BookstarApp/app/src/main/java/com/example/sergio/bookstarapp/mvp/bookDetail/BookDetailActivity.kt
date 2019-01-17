package com.example.sergio.bookstarapp.mvp.bookDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.sergio.bookstarapp.R
import com.example.sergio.bookstarapp.api.Model.Book
import com.example.sergio.bookstarapp.mvp.bookDetail.BookDetailFragment.BookDetailFragmentInteractionListener
import com.google.gson.Gson

class BookDetailActivity : AppCompatActivity(), BookDetailFragmentInteractionListener {

  private var bookDetailFragment: BookDetailFragment? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_book_detail)
    val gson = Gson()
    val book = gson.fromJson<Book>(intent.getStringExtra("book"), Book::class.java)
    bookDetailFragment =
        supportFragmentManager.findFragmentById(R.id.book_detail_fragment) as BookDetailFragment?
    bookDetailFragment!!.updateDetails(book)
  }

  override fun onFavoritePressedFragmentInteraction(
    book: Book,
    isFavorite: Boolean
  ) {

  }
}
