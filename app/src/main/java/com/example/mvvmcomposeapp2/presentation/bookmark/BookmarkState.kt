package com.example.mvvmcomposeapp2.presentation.bookmark

import com.example.mvvmcomposeapp2.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)