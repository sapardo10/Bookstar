package com.example.sergio.bookstarapp.mvp.favorites

import com.example.sergio.bookstarapp.room.BookEntity

interface FavoritesView {
  fun updateBooksList(books: List<BookEntity>)
}
