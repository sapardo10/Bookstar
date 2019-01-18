package com.example.sergio.bookstarapp.mvp.interactor

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.example.sergio.bookstarapp.api.Model.Book
import com.example.sergio.bookstarapp.room.BookDao
import com.example.sergio.bookstarapp.room.BookEntity
import com.example.sergio.bookstarapp.room.BookRoomDatabase

class BookInteractor(val context: Context) {

  private var db: BookRoomDatabase? = null
  private var bookDao: BookDao? = null
  private var booksLiveData: LiveData<List<BookEntity>>

  init {
    db = BookRoomDatabase.getBookDatabase(context = context)
    bookDao = db?.bookDao()!!
    booksLiveData = bookDao?.getAllBooks()!!
  }

  fun getAllBooks(): LiveData<List<BookEntity>> {
    return booksLiveData
  }

  fun saveFavorite(
    book: Book,
    isFavorite: Boolean
  ) {
    var author = ""
    if (book.authorsName != null)
      author = book.authorsName?.get(0)
    var bookToSave = BookEntity(
        book.key, book.title, author, isFavorite, book.coverId, book.hasFullText, book.editionCount,
        book.firstPublishYear
    )
    if (isFavorite) {
      insert(bookToSave)
    } else {
      delete(bookToSave)
    }

  }

  fun saveFavorite(
    book: BookEntity,
    isFavorite: Boolean
  ) {
    if (isFavorite) {
      insert(book)
    } else {
      delete(book)
    }

  }

  fun insert(book: BookEntity) {
    insertAsyncTask(bookDao!!).execute(book)
  }

  fun delete(book: BookEntity) {
    deleteAsyncTask(bookDao!!).execute(book)
  }

  private class insertAsyncTask internal constructor(private val mAsyncTaskDao: BookDao) : AsyncTask<BookEntity, Void, Void>() {
    override fun doInBackground(vararg params: BookEntity): Void? {
      try {
        mAsyncTaskDao.insert(params[0])
      } catch (e: Exception) {
        Log.d("ERROR", "The element already exist on the database")
      }

      return null
    }
  }

  private class deleteAsyncTask internal constructor(private val mAsyncTaskDao: BookDao) : AsyncTask<BookEntity, Void, Void>() {
    override fun doInBackground(vararg params: BookEntity): Void? {
      mAsyncTaskDao.deleteBook(params[0])
      return null
    }
  }

}