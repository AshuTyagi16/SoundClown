package com.sasuke.soundclown.ui.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sasuke.soundclown.data.model.ItemCategories
import kotlinx.android.synthetic.main.cell_category.view.*

class SearchCategoryViewHolder(itemView: View, private val glide: RequestManager) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var onCategoryItemClickListener: OnCategoryItemClickListener

    private val requestOptions = RequestOptions().override(Target.SIZE_ORIGINAL)

    fun setCategory(itemCategories: ItemCategories) {
        glide.load(itemCategories.iconList[0].url)
            .apply(requestOptions)
            .into(itemView.ivCategory)

        itemView.tvCategoryName.text = itemCategories.name

        itemView.setOnClickListener {
            if (::onCategoryItemClickListener.isInitialized)
                onCategoryItemClickListener.onSearchedCategoryClicked(
                    itemCategories.id,
                    itemCategories.name,
                    adapterPosition
                )
        }
    }

    fun setOnCategoryItemClickListener(onCategoryItemClickListener: OnCategoryItemClickListener) {
        this.onCategoryItemClickListener = onCategoryItemClickListener
    }

    interface OnCategoryItemClickListener {
        fun onSearchedCategoryClicked(categoryId: String, categoryName: String, position: Int)
    }
}