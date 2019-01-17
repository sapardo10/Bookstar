package com.example.sergio.bookstarapp.mvp.mainActivity

import com.example.sergio.bookstarapp.api.Model

interface MainActivityView {
  fun updateBooksList(books: List<Model.Book>)
  fun showErrorToast(error: String?)
}