package com.example.sergio.bookstarapp.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface BookDao {
  @Insert
  fun insert(book: BookEntity)

  @Delete
  fun deleteBook(book: BookEntity)

  @Query("SELECT * FROM books")
  fun getAllBooks(): List<BookEntity>

}