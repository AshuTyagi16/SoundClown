package com.sasuke.soundclown.ui.category_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.ItemPlaylist

class CategoryDetailsAdapter(private val glide: RequestManager) :
    RecyclerView.Adapter<CategoryDetailsViewHolder>(),
    CategoryDetailsViewHolder.OnCategoryDetailsItemClickListener {

    private lateinit var onCategoryDetailsItemClickListener: OnCategoryDetailsItemClickListener
    private lateinit var playlistItemList: ArrayList<ItemPlaylist>

    fun setItems(list: ArrayList<ItemPlaylist>) {
        this.playlistItemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cell_category_detail, parent, false)
        return CategoryDetailsViewHolder(view, glide)
    }

    override fun getItemCount(): Int {
        if (::playlistItemList.isInitialized)
            return playlistItemList.size
        return 0
    }

    override fun onBindViewHolder(holder: CategoryDetailsViewHolder, position: Int) {
        if (::playlistItemList.isInitialized){
            holder.setItem(playlistItemList[position])
            holder.setOnCategoryItemClickListener(this)
        }
    }

    fun setOnCategoryDetailsItemClickListener(onCategoryItemClickListener: OnCategoryDetailsItemClickListener) {
        this.onCategoryDetailsItemClickListener = onCategoryItemClickListener
    }

    interface OnCategoryDetailsItemClickListener {
        fun onCategoryDetailsClicked()
    }

    override fun onCategoryDetailsClicked() {
        if (::onCategoryDetailsItemClickListener.isInitialized)
            onCategoryDetailsItemClickListener.onCategoryDetailsClicked()
    }
}