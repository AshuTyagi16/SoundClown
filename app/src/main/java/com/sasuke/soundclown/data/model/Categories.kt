package com.sasuke.soundclown.data.model

data class Categories(
    val href: String,
    val items: List<ItemX>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)