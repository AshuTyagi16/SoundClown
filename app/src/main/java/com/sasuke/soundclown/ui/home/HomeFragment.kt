package com.sasuke.soundclown.ui.home

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

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    @Inject
    lateinit var itemDecorator: ItemDecorator

    private lateinit var homeFragmentViewModel: HomeFragmentViewModel

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_search
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        setupRecyclerView()
        getData()
        observeLiveData()
    }

    private fun inject() {
        homeFragmentViewModel =
            ViewModelProvider(this, viewModelFactory).get(HomeFragmentViewModel::class.java)
    }

    private fun setupRecyclerView() {
        rvCategories.layoutManager = gridLayoutManager
        rvCategories.adapter = categoryAdapter
        rvCategories.addItemDecoration(itemDecorator)
    }

    private fun getData() {
        homeFragmentViewModel.getCategories()
    }

    private fun observeLiveData() {
        homeFragmentViewModel.categoryLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    it.data?.let {
                        categoryAdapter.setItems(it.categories.items)
                    }
                }
                Status.ERROR -> {
                }
            }
        })
    }
}