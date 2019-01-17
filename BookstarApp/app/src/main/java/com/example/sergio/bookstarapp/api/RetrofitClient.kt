package com.example.sergio.bookstarapp.api

import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

  private var mCompositeDisposable = CompositeDisposable()

  companion object {
    private const val API_URL = "http://openlibrary.org/"

    fun createBookService(): RetrofitBooksService {
      val retrofit = Retrofit.Builder()
          .baseUrl(API_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build()
      return retrofit.create(RetrofitBooksService::class.java)
    }
  }

}