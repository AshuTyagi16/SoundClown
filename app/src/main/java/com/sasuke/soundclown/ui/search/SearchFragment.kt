package com.sasuke.soundclown.ui.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.Status
import com.sasuke.soundclown.ui.base.BaseFragment
import com.sasuke.soundclown.ui.base.ItemDecorator
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment(),
    SearchCategoryAdapter.OnCategoryItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var searchCategoryAdapter: SearchCategoryAdapter

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    @Inject
    lateinit var itemDecorator: ItemDecorator

    private lateinit var searchFragmentViewModel: SearchFragmentViewModel
    private lateinit var onCategoryItemClickListener: OnCategoryItemClickListener


    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_search
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        initialiseLayout()
        getData()
        observeLiveData()
    }

    private fun inject() {
        searchFragmentViewModel =
            ViewModelProvider(this, viewModelFactory).get(SearchFragmentViewModel::class.java)
    }

    private fun initialiseLayout() {
        rvCategories.layoutManager = gridLayoutManager
        rvCategories.adapter = searchCategoryAdapter
        rvCategories.addItemDecoration(itemDecorator)
        searchCategoryAdapter.setOnCategoryItemClickListener(this)
    }

    private fun getData() {
        searchFragmentViewModel.getCategories()
    }

    private fun observeLiveData() {
        searchFragmentViewModel.categoryLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    it.data?.let {
                        searchCategoryAdapter.setItems(it.categories.itemCategories)
                    }
                }
                Status.ERROR -> {
                }
            }
        })
    }

    override fun onSearchedCategoryClicked(categoryId: String, categoryName: String, position: Int) {
        if (::onCategoryItemClickListener.isInitialized)
            onCategoryItemClickListener.onSearchedCategoryClicked(categoryId, categoryName, position)
    }


    fun setOnCategoryItemClickListener(onCategoryItemClickListener: OnCategoryItemClickListener) {
        this.onCategoryItemClickListener = onCategoryItemClickListener
    }

    interface OnCategoryItemClickListener {
        fun onSearchedCategoryClicked(categoryId: String, categoryName: String, position: Int)
    }
}