package com.example.sergio.bookstarapp.mvp.bookDetail

import com.example.sergio.bookstarapp.api.FullModel.ApiResponseFullBook
import com.example.sergio.bookstarapp.api.FullModel.FullBook
import com.example.sergio.bookstarapp.api.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BookDetailFragmentPresenter(val mView: BookDetailFragment) {
  private val retrofit by lazy {
    RetrofitClient.createBookService()
  }

  private var disposable: Disposable? = null

  /**
   * Call to the service to get details about one book
   */
  fun getBook(id: String) {
    var url = buildKeyForURL(id)
    disposable = retrofit.getBook(url)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { apiResponse: ApiResponseFullBook -> showDetailsBook(apiResponse.fullBook) },
            { error -> showError(error.message) }
        )
  }

  /**
   * It builds the necessary url for the service to be consumed
   */
  private fun buildKeyForURL(id: String): String {
    var list = id.split("/")
    if (list.isNotEmpty())
      return "OLID:" + list[list.size - 1].replace("W", "M")
    else
      return "OLID"
  }

  private fun showDetailsBook(book: FullBook) {
    mView.addDetailsFromApi(book)
  }

  private fun showError(error: String?) {
    mView.showErrorToast(error)
  }

  /**
   * It concatenates the authors name in case there is more than one
   * @authorsName array containing the authors of a book
   * @return string with all the names of the authors of a book
   */
  fun getNameAuthors(authorsName: Array<String>?): String {
    var res = "No authors"
    if (authorsName != null) {
      res = ""
      for (author in authorsName) {
        res += "$author "
      }
    }
    return res
  }
}