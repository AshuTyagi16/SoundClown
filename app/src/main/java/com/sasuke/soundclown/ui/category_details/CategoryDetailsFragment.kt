package com.sasuke.soundclown.ui.category_details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.Status
import com.sasuke.soundclown.ui.base.BaseFragment
import com.sasuke.soundclown.ui.base.ItemDecorator
import com.sasuke.soundclown.util.gone
import com.sasuke.soundclown.util.visible
import kotlinx.android.synthetic.main.fragment_category_details.*
import javax.inject.Inject

class CategoryDetailsFragment : BaseFragment(),
    CategoryDetailsAdapter.OnCategoryDetailsItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var categoryDetailsAdapter: CategoryDetailsAdapter

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    @Inject
    lateinit var itemDecorator: ItemDecorator

    private lateinit var categoryId: String
    private lateinit var categoryName: String
    private lateinit var categoryDetailsViewModel: CategoryDetailsViewModel
    private lateinit var onCategoryDetailsItemClickListener: OnCategoryDetailsItemClickListener

    companion object {
        private const val EXTRA_CATEGORY_ID = "EXTRA_CATEGORY_ID"
        private const val EXTRA_CATEGORY_NAME = "EXTRA_CATEGORY_NAME"
        fun newInstance(categoryId: String, categoryName: String): CategoryDetailsFragment {
            val fragment = CategoryDetailsFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_CATEGORY_ID, categoryId)
            bundle.putString(EXTRA_CATEGORY_NAME, categoryName)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_category_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        getArgument()
        initialiseLayout()
        getData()
        setClickListeners()
        observeLiveData()
    }

    private fun initViewModel() {
        categoryDetailsViewModel =
            ViewModelProvider(this, viewModelFactory).get(CategoryDetailsViewModel::class.java)
    }

    private fun getArgument() {
        arguments?.let {
            it.getString(EXTRA_CATEGORY_ID)?.let {
                categoryId = it
            }
            it.getString(EXTRA_CATEGORY_NAME)?.let {
                categoryName = it
            }
        }
    }

    private fun initialiseLayout() {
        if (::categoryName.isInitialized)
            tvHeader.text = categoryName

        rvCategoriesDetails.layoutManager = gridLayoutManager
        rvCategoriesDetails.adapter = categoryDetailsAdapter
        rvCategoriesDetails.addItemDecoration(itemDecorator)
        categoryDetailsAdapter.setOnCategoryDetailsItemClickListener(this)
    }

    private fun getData() {
        if (::categoryId.isInitialized)
            categoryDetailsViewModel.getCategoryPlaylist(categoryId)
    }

    private fun setClickListeners() {
        ivBack.setOnClickListener {
            if (::onCategoryDetailsItemClickListener.isInitialized)
                onCategoryDetailsItemClickListener.onRemoveCategoryDetailsFragment()
        }
    }

    private fun observeLiveData() {
        categoryDetailsViewModel.playlistLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    rvCategoriesDetails.gone()
                    progressBar.visible()
                }
                Status.SUCCESS -> {
                    rvCategoriesDetails.visible()
                    progressBar.gone()
                    it.data?.let {
                        categoryDetailsAdapter.setItems(it.playlists.playlistItemList)
                    }
                }
                Status.ERROR -> {
                    rvCategoriesDetails.gone()
                    progressBar.gone()
                }
            }
        })
    }

    fun setOnCategoryDetailsItemClickListener(onCategoryItemClickListener: OnCategoryDetailsItemClickListener) {
        this.onCategoryDetailsItemClickListener = onCategoryItemClickListener
    }

    interface OnCategoryDetailsItemClickListener {
        fun onCategoryClicked()
        fun onRemoveCategoryDetailsFragment()
    }

    override fun onCategoryDetailsClicked() {
        if (::onCategoryDetailsItemClickListener.isInitialized)
            onCategoryDetailsItemClickListener.onCategoryClicked()
    }
}