package com.example.mvvmcomposeapp2.di

import android.app.Application
import com.example.mvvmcomposeapp2.data.manager.LocalUserMangerImpl
import com.example.mvvmcomposeapp2.data.remote.NewsApi
import com.example.mvvmcomposeapp2.data.repository.NewsRepositoryImpl
import com.example.mvvmcomposeapp2.domain.manger.LocalUserManger
import com.example.mvvmcomposeapp2.domain.repository.NewsRepository
import com.example.mvvmcomposeapp2.domain.usecases.app_entry.AppEntryUseCases
import com.example.mvvmcomposeapp2.domain.usecases.app_entry.ReadAppEntry
import com.example.mvvmcomposeapp2.domain.usecases.app_entry.SaveAppEntry
import com.example.mvvmcomposeapp2.domain.usecases.news.GetNews
import com.example.mvvmcomposeapp2.domain.usecases.news.NewsUseCases
import com.example.mvvmcomposeapp2.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun provideApiInstance(): NewsApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
        )
    }

}