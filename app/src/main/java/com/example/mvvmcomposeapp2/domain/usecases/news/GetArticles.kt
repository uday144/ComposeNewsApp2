package com.example.mvvmcomposeapp2.domain.usecases.news

import com.example.mvvmcomposeapp2.data.local.NewsDao
import com.example.mvvmcomposeapp2.domain.model.Article
import kotlinx.coroutines.flow.Flow

class GetArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>>{
        return newsDao.getArticles()
    }

}