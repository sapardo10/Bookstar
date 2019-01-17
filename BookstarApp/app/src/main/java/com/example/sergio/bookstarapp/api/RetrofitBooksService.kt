package com.example.sergio.bookstarapp.api

import com.example.sergio.bookstarapp.api.Model.ApiResponseSearch
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitBooksService {
  @GET("/search.json")
  fun searchBooks(@Query("q") title: String): Observable<ApiResponseSearch>
}