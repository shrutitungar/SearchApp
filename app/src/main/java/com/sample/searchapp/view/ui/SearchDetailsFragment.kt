package com.sample.searchapp.view.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.sample.searchapp.R
import com.sample.searchapp.data.ImageResult
import com.sample.searchapp.data.SearchResult
import com.sample.searchapp.viewmodel.SearchViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search_details.*

class SearchDetailsFragment : Fragment(R.layout.fragment_search_details) {

    private lateinit var mSearchViewModel: SearchViewModel
    private var mSearchResult: SearchResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSearchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        if (arguments != null) {
            val args = SearchDetailsFragmentArgs.fromBundle(requireArguments())
            mSearchResult = args.searchDetails
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.toolbar?.title = mSearchResult?.title

        Picasso.get().load(mSearchResult?.imageResult?.get(0)?.link)
            .placeholder(R.mipmap.ic_launcher)
            .into(iv_detail)

        mSearchResult?.imageResult?.get(0)?.image_id?.let {
            mSearchViewModel.getImageInfo(it)?.observe(
                viewLifecycleOwner, this::getImageResult
            )
        }

        btn_submit.setOnClickListener {
            val comment: String = et_comment.text.toString()
            if (comment.isNotEmpty()) {
                mSearchResult?.imageResult?.get(0)?.comment = comment
                mSearchResult?.imageResult?.get(0)
                    ?.let { it1 -> mSearchViewModel.setImageInfo(it1) }
            } else Toast.makeText(activity, "Please enter some comment", Toast.LENGTH_LONG).show()
        }
    }

    private fun getImageResult(imageResult: ImageResult) {
        if (!imageResult.comment.isNullOrBlank()) {
            et_comment.visibility = View.GONE
            btn_submit.visibility = View.GONE
            tv_comment.visibility = View.VISIBLE
            tv_comment.text = imageResult.comment
        }
        else {
            et_comment.visibility = View.VISIBLE
            btn_submit.visibility = View.VISIBLE
            tv_comment.visibility = View.GONE
        }
    }
}