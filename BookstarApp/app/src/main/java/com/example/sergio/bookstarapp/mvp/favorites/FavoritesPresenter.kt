package com.example.sergio.bookstarapp.mvp.favorites

import android.arch.lifecycle.LiveData
import android.content.Context
import com.example.sergio.bookstarapp.mvp.interactor.BookInteractor
import com.example.sergio.bookstarapp.room.BookEntity

class FavoritesPresenter(private val mView: FavoritesView) {

  var bookInteractor: BookInteractor? = null
  private val allFavorites: LiveData<List<BookEntity>>

  init {
    bookInteractor = BookInteractor(mView as Context)
    allFavorites = bookInteractor!!.getAllBooks()
  }

}