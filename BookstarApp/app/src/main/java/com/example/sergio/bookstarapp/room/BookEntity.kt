package com.example.sergio.bookstarapp.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
  @PrimaryKey val key: String,
  val title: String,
  val author: String,
  val isFavorite: Boolean,
  val coverId: Long,
  val hasFullText: Boolean,
  val editionCount: Int,
  val firstPublishYear: Int
)