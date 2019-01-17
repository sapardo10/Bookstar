package com.example.sergio.bookstarapp.api

import com.google.gson.annotations.SerializedName

object Model {
  data class ApiResponseSearch(
    @SerializedName("start") val start: Int,
    @SerializedName("num_found") val numFound: Int,
    @SerializedName("docs") val docs: List<Book>
  )

  data class Book(
    @SerializedName("cover_i") val coverId: Int,
    @SerializedName("has_fulltext") val hasFullText: Boolean,
    @SerializedName("edition_count") val editionCount: Int,
    @SerializedName("title") val title: String,
    @SerializedName("author_name") val authorsName: Array<String>,
    @SerializedName("key") val key: String,
    @SerializedName("first_publish_year") val firstPublishYear: Int
  )
}



