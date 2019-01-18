package com.example.sergio.bookstarapp.mvp.mainActivity

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.sergio.bookstarapp.R

class SearchBarFragment : Fragment() {
  private var listener: SearchBarFragmentInteractionListener? = null

  @BindView(R.id.search_field) lateinit var searchField: EditText
  @BindView(R.id.search_button) lateinit var searchButton: ImageView

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    var view = inflater.inflate(R.layout.fragment_search_bar, container, false)
    ButterKnife.bind(this, view)
    return view
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    bindClickListenerToSearchButton()
  }

  private fun bindClickListenerToSearchButton() {
    searchButton.setOnClickListener {
      var searchText = searchField.text.toString()
      onSearchButtonPressed(searchText)
    }
  }

  private fun onSearchButtonPressed(searchText: String) {
    listener?.onSearchFragmentInteraction(searchText)
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if (context is SearchBarFragmentInteractionListener) {
      listener = context
    } else {
      throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
    }
  }

  override fun onDetach() {
    super.onDetach()
    listener = null
  }

  interface SearchBarFragmentInteractionListener {

    /**
     * It handles the behaviour of the app when the users presses the search button
     */
    fun onSearchFragmentInteraction(searchText: String)
  }

}
