package com.example.mvvmcomposeapp2.domain.usecases.news

import com.example.mvvmcomposeapp2.data.local.NewsDao
import com.example.mvvmcomposeapp2.domain.model.Article


class GetArticle (
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(url: String): Article?{
        return newsDao.getArticle(url = url)
    }

}