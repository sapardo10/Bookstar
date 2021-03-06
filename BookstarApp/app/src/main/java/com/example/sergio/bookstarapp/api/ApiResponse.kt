package com.example.sergio.bookstarapp.api

import com.google.gson.annotations.SerializedName
import java.util.Arrays

object Model {

  /**
   * It collects the information when using the search endpoint of the API
   */
  data class ApiResponseSearch(
    @SerializedName("start") val start: Int,
    @SerializedName("num_found") val numFound: Int,
    @SerializedName("docs") val docs: List<Book>
  )

  data class Book(
    @SerializedName("cover_i") val coverId: Long,
    @SerializedName("has_fulltext") val hasFullText: Boolean,
    @SerializedName("edition_count") val editionCount: Int,
    @SerializedName("title") val title: String,
    @SerializedName("author_name") val authorsName: Array<String>,
    @SerializedName("key") val key: String,
    @SerializedName("first_publish_year") val firstPublishYear: Int
  ) {
    override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (javaClass != other?.javaClass) return false

      other as Book

      if (coverId != other.coverId) return false
      if (hasFullText != other.hasFullText) return false
      if (editionCount != other.editionCount) return false
      if (title != other.title) return false
      if (!Arrays.equals(authorsName, other.authorsName)) return false
      if (key != other.key) return false
      if (firstPublishYear != other.firstPublishYear) return false

      return true
    }

    override fun hashCode(): Int {
      var result = coverId.toInt()
      result = 31 * result + hasFullText.hashCode()
      result = 31 * result + editionCount
      result = 31 * result + title.hashCode()
      result = 31 * result + Arrays.hashCode(authorsName)
      result = 31 * result + key.hashCode()
      result = 31 * result + firstPublishYear
      return result
    }
  }

}



