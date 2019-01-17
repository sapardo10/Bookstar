package com.example.sergio.bookstarapp.mvp.mainActivity

import com.example.sergio.bookstarapp.api.Model.ApiResponseSearch
import com.example.sergio.bookstarapp.api.Model.Book
import com.example.sergio.bookstarapp.api.RetrofitClient
import com.example.sergio.bookstarapp.mvp.dummy.BookItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val mView: MainActivityView?) {

  private val retrofit by lazy {
    RetrofitClient.createBookService()
  }

  var disposable: Disposable? = null

  fun start() {
  }

  fun searchBooks(searchText: String) {
    disposable = retrofit.searchBooks(searchText)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { apiResponse: ApiResponseSearch -> showBooksList(apiResponse.docs) },
            { error -> showError(error.message) }
        )
  }

  private fun showBooksList(books: List<Book>) {
    mView?.updateBooksList(books)
  }

  private fun showError(error: String?) {
    mView?.showErrorToast(error)
  }

  fun updateBookDetail(book: BookItem) {
  }
}

