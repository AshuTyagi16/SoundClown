package com.sasuke.soundclown.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.ItemCategories

class SearchCategoryAdapter(private val glide: RequestManager) :
    RecyclerView.Adapter<SearchCategoryViewHolder>(), SearchCategoryViewHolder.OnCategoryItemClickListener {

    private lateinit var items: ArrayList<ItemCategories>
    private lateinit var onCategoryItemClickListener: OnCategoryItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_category, parent, false)
        return SearchCategoryViewHolder(view, glide)
    }

    override fun getItemCount(): Int {
        return if (::items.isInitialized) items.size else 0
    }

    override fun onBindViewHolder(holderSearch: SearchCategoryViewHolder, position: Int) {
        if (::items.isInitialized) {
            holderSearch.setCategory(items[position])
            holderSearch.setOnCategoryItemClickListener(this)
        }
    }

    fun setItems(list: ArrayList<ItemCategories>) {
        this.items = list
        notifyDataSetChanged()
    }

    fun setOnCategoryItemClickListener(onCategoryItemClickListener: OnCategoryItemClickListener){
        this.onCategoryItemClickListener = onCategoryItemClickListener
    }

    interface OnCategoryItemClickListener {
        fun onSearchedCategoryClicked(categoryId: String, categoryName: String, position: Int)
    }

    override fun onSearchedCategoryClicked(categoryId: String, categoryName: String, position: Int) {
        if (::onCategoryItemClickListener.isInitialized)
            onCategoryItemClickListener.onSearchedCategoryClicked(categoryId, categoryName, position)
    }
}