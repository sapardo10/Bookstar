package com.example.sergio.bookstarapp.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [BookEntity::class], version = 1)
abstract class BookRoomDatabase : RoomDatabase() {

  abstract fun bookDao(): BookDao

  companion object {
    var INSTANCE: BookRoomDatabase? = null

    fun getBookDatabase(context: Context): BookRoomDatabase? {
      if (INSTANCE == null) {
        synchronized(BookRoomDatabase::class) {
          INSTANCE =
              Room.databaseBuilder(
                  context.applicationContext, BookRoomDatabase::class.java, "bookDB"
              )
                  .build()
        }
      }
      return INSTANCE
    }

  }

}