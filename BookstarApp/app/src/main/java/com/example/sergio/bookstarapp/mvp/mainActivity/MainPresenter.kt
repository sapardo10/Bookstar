package com.example.sergio.bookstarapp.mvp.mainActivity

class MainPresenter(val mView: MainActivityView?) {
  companion object {
    val SEARCH_BY_AUTHOR = 0
    val SEARCH_BY_TITLE = 1
  }

  var searchBy = SEARCH_BY_AUTHOR

  fun changeSearchType(searchType: Int) {
    searchBy = searchType
  }

}