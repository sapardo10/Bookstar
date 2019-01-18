package com.example.sergio.bookstarapp.api

import com.google.gson.annotations.SerializedName

object FullModel {
  /**
   * It collects the information when using the book endpoint of the API
   */
  data class ApiResponseFullBook(
    val fullBook: FullBook
  )

  data class FullBook(
    @SerializedName("weight") val weight: String,
    @SerializedName("number_of_pages") val numberOfPages: Int,
    @SerializedName("publish_places") val publishPlaces: List<Place>
  )

  data class Place(
    @SerializedName("name") val name: String
  )
}