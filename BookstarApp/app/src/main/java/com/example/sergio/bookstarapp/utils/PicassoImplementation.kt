package com.example.sergio.bookstarapp.utils

import android.content.Context
import android.widget.ImageView
import com.example.sergio.bookstarapp.R
import com.squareup.picasso.Picasso

class PicassoImplementation {

  companion object {

    const val KEY_OLID = "OLID"

    fun loadImageOnView(
      context: Context,
      imageView: ImageView,
      uri: String
    ) {
      Picasso.with(context)
          .load(uri)
          .fit()
          .centerCrop()
          .placeholder(R.drawable.book_placeholder)
          .error(R.drawable.book_placeholder)
          .into(imageView)
    }

    fun generateFinalUrl(
      value: String,
      size: String
    ): String {
      var uri =
        "http://covers.openlibrary.org/b/ID/$value-$size.jpg"
      return uri
    }
  }
}