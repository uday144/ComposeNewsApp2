package com.example.mvvmcomposeapp2.presentation.details

import com.example.mvvmcomposeapp2.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}