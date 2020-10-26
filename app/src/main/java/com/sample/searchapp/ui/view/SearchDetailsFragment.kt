package com.sample.searchapp.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sample.searchapp.R
import com.sample.searchapp.data.SearchResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_search_details.*

class SearchDetailsFragment : Fragment(R.layout.fragment_search_details) {

    private var mSearchResult: SearchResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val args = SearchDetailsFragmentArgs.fromBundle(requireArguments())
            mSearchResult = args.searchDetails
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(mSearchResult?.imageResult?.get(0)?.link)
            .placeholder(R.mipmap.ic_launcher)
            .into(iv_detail)
    }
}