package com.example.mvvmcomposeapp2.domain.repository

import androidx.paging.PagingData
import com.example.mvvmcomposeapp2.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}