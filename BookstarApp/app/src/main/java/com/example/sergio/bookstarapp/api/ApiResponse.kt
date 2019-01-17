package com.example.sergio.bookstarapp.api

object Model {
  data class ApiResponseSearch(
    val title: String,
    val numFound: Int,
    val docs: List<Book>
  )

  data class Book(
    val coverI: Int,
    val hasFullText: Boolean,
    val editionCount: Int,
    val title: String,
    val authorsNames: List<Author>,
    val key: String
  )

  data class Author(val name: String)
}



