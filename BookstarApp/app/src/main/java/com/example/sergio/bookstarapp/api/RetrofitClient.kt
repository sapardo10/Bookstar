package com.example.sergio.bookstarapp.api

import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

  companion object {
    private val API_URL = "http://openlibrary.org/"
    lateinit var retrofit: Retrofit

    fun getRetrofitInstance(): Retrofit {
      if (retrofit == null) {
        retrofit = Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
      }
      return retrofit
    }
  }
}