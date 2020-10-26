package com.sample.searchapp.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sample.searchapp.R
import com.sample.searchapp.data.SearchResponse
import com.sample.searchapp.data.SearchResult
import com.sample.searchapp.data.remote.APIResponse
import com.sample.searchapp.ui.adapter.GridSpacingItemDecoration
import com.sample.searchapp.ui.adapter.SearchResultAdapter
import com.sample.searchapp.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.fragment_search),
    SearchResultAdapter.OnSearchItemClickListener {

    private lateinit var searchResultAdapter: SearchResultAdapter
    private var mSearchResultList: List<SearchResult>? = ArrayList()
    private lateinit var mSearchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSearchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        mSearchViewModel.getSearchResultsLiveData()?.observe(this, this::getSearchResponse)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchResultAdapter =
            SearchResultAdapter(activity?.applicationContext)
        searchResultAdapter.setOnSearchItemClickListener(this)
        rv_images.adapter = searchResultAdapter
        rv_images.layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        rv_images.addItemDecoration(GridSpacingItemDecoration(3, 20, false))

        /*search_layout.setOnClickListener {
            val translation = AnimationUtils.loadAnimation(
                activity?.applicationContext,
                R.anim.search_layout_anim
            )
            search_layout.startAnimation(translation);
        }*/

        val watcher = object : TextWatcher {
            private var searchFor = ""

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()
                if (searchText == searchFor)
                    return

                searchFor = searchText

                GlobalScope.launch {
                    delay(250)  //debounce timeOut
                    if (searchText != searchFor)
                        return@launch

                    mSearchViewModel.getSearchResponse(1, searchFor)
                }
            }

            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit
        }
        et_search.addTextChangedListener(watcher)
    }

    private fun getSearchResponse(apiResponse: APIResponse) {
        when (apiResponse.status) {
            APIResponse.Status.LOADING -> progress_bar.visibility = View.VISIBLE
            APIResponse.Status.SUCCESS -> {
                progress_bar.visibility = View.GONE
                if (apiResponse.data != null) {
                    val searchResponse: SearchResponse = apiResponse.data as SearchResponse
                    mSearchResultList = ArrayList()
                    searchResponse.data.forEach { searchRes ->
                        searchRes.imageResult?.let {
                            if (it[0].type.equals("image/png") || it[0].type.equals("image/jpeg")) {
                                (mSearchResultList as ArrayList<SearchResult>).add(searchRes)
                            }
                        }
                    }
//                    mSearchResultList = searchResponse.data
                    searchResultAdapter.setItems(mSearchResultList)
                }
            }
            APIResponse.Status.ERROR -> {
                progress_bar.visibility = View.GONE
            }
        }
    }

    override fun onImageClick(v: View, searchResult: SearchResult?) {
        val action = SearchFragmentDirections.actionNavSearchToNavSearchDetails(searchResult)
        v.findNavController().navigate(action)
    }
}