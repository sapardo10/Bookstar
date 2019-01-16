package com.example.sergio.bookstarapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitBooksService {
  @GET("/search.json")
  fun searchBooks(@Query("q") title: String): Call<List<Book>>
}