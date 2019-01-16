package com.example.sergio.bookstarapp.mvp.mainActivity

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainPresenterTest {

  private lateinit var presenter: MainPresenter

  @Before fun setUp() {
    presenter = MainPresenter(null)
  }

  @Test fun changeSearchType() {
    Assert.assertEquals(MainPresenter.SEARCH_BY_AUTHOR, presenter.searchBy)
    presenter.changeSearchType(MainPresenter.SEARCH_BY_TITLE)
    Assert.assertEquals(MainPresenter.SEARCH_BY_TITLE, presenter.searchBy)
  }

}