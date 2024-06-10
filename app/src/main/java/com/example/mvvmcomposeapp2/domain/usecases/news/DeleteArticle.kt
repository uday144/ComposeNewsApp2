package com.example.mvvmcomposeapp2.domain.usecases.news

import com.example.mvvmcomposeapp2.data.local.NewsDao
import com.example.mvvmcomposeapp2.domain.model.Article


class DeleteArticle (
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.delete(article = article)
    }

}