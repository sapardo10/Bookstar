package com.example.sergio.bookstarapp.mvp.bookDetail

import android.content.Context
import com.example.sergio.bookstarapp.api.Model.Book
import com.example.sergio.bookstarapp.mvp.interactor.BookInteractor

class BookDetailPresenter(private val mView: BookDetailView?) {
  var bookInteractor: BookInteractor? = null

  init {
    bookInteractor = BookInteractor(mView as Context)
  }

  fun saveFavorite(
    book: Book,
    isFavorite: Boolean
  ) {
    bookInteractor!!.saveFavorite(book, isFavorite)
  }
}