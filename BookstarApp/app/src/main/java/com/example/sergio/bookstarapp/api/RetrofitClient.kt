package com.example.sergio.bookstarapp.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

  companion object {
    /**End point used*/
    private const val API_URL = "http://openlibrary.org/"

    private var retrofit: Retrofit? = null

    private fun getRetrofitInstance(): Retrofit? {
      if (retrofit == null) {
        retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
      }
      return retrofit
    }

    fun createBookService(): RetrofitBooksService {
      return getRetrofitInstance()!!.create(RetrofitBooksService::class.java)
    }
  }

}