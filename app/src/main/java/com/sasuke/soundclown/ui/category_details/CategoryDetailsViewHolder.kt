package com.sasuke.soundclown.ui.category_details

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sasuke.soundclown.data.model.ItemPlaylist
import kotlinx.android.synthetic.main.cell_category_detail.view.*

class CategoryDetailsViewHolder(itemView: View, private val glide: RequestManager) :
    RecyclerView.ViewHolder(itemView) {

    private val requestOptions = RequestOptions().override(Target.SIZE_ORIGINAL)
    private lateinit var onCategoryDetailsItemClickListener: OnCategoryDetailsItemClickListener

    fun setItem(playlist: ItemPlaylist) {
        glide.load(playlist.imageList[0].url)
            .apply(requestOptions)
            .into(itemView.ivCategory)

        itemView.tvName.text = playlist.name
        itemView.tvFollowers.text = playlist.description

        itemView.setOnClickListener {
            if (::onCategoryDetailsItemClickListener.isInitialized)
                onCategoryDetailsItemClickListener.onCategoryDetailsClicked()
        }
    }

    fun setOnCategoryItemClickListener(onCategoryItemClickListener: OnCategoryDetailsItemClickListener) {
        this.onCategoryDetailsItemClickListener = onCategoryItemClickListener
    }

    interface OnCategoryDetailsItemClickListener {
        fun onCategoryDetailsClicked()
    }
}