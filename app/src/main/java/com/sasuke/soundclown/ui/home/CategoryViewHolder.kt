package com.sasuke.soundclown.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sasuke.soundclown.data.model.ItemX
import kotlinx.android.synthetic.main.cell_category.view.*

class CategoryViewHolder(itemView: View, private val glide: RequestManager) :
    RecyclerView.ViewHolder(itemView) {

    private val requestOptions = RequestOptions().override(Target.SIZE_ORIGINAL)

    fun setCategory(itemX: ItemX) {
        glide.load(itemX.icons[0].url)
            .apply(requestOptions)
            .into(itemView.ivCategory)
    }
}