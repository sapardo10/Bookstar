package com.example.sergio.bookstarapp.utils

import android.content.Context
import android.widget.ImageView
import com.example.sergio.bookstarapp.R
import com.squareup.picasso.Picasso

/**
 * Class that uses picasso to load images into imageViews
 */
class PicassoImplementation {

  companion object {

    /**
     * load image from uri into an imageview, put a placeholder while the image loads
     */
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

    /**
     * Generate the url to get an image from the api according to documentation
     */
    fun generateFinalUrl(
      value: String,
      size: String
    ): String {
      return "http://covers.openlibrary.org/b/ID/$value-$size.jpg"
    }
  }
}