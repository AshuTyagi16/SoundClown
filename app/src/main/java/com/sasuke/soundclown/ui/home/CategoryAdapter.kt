package com.sasuke.soundclown.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.ItemX

class CategoryAdapter(private val glide: RequestManager) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private lateinit var items: List<ItemX>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_category, parent, false)
        return CategoryViewHolder(view, glide)
    }

    override fun getItemCount(): Int {
        return if (::items.isInitialized) items.size else 0
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if (::items.isInitialized) {
            holder.setCategory(items[position])
        }
    }

    fun setItems(list: List<ItemX>) {
        this.items = list
        notifyDataSetChanged()
    }
}