package com.example.mvvmcomposeapp2.di

import android.app.Application
import androidx.room.Room
import com.example.mvvmcomposeapp2.data.local.NewsDao
import com.example.mvvmcomposeapp2.data.local.NewsDatabase
import com.example.mvvmcomposeapp2.data.local.NewsTypeConvertor
import com.example.mvvmcomposeapp2.data.manager.LocalUserMangerImpl
import com.example.mvvmcomposeapp2.data.remote.NewsApi
import com.example.mvvmcomposeapp2.data.repository.NewsRepositoryImpl
import com.example.mvvmcomposeapp2.domain.manger.LocalUserManger
import com.example.mvvmcomposeapp2.domain.repository.NewsRepository
import com.example.mvvmcomposeapp2.domain.usecases.app_entry.AppEntryUseCases
import com.example.mvvmcomposeapp2.domain.usecases.app_entry.ReadAppEntry
import com.example.mvvmcomposeapp2.domain.usecases.app_entry.SaveAppEntry
import com.example.mvvmcomposeapp2.domain.usecases.news.DeleteArticle
import com.example.mvvmcomposeapp2.domain.usecases.news.GetArticle
import com.example.mvvmcomposeapp2.domain.usecases.news.GetArticles
import com.example.mvvmcomposeapp2.domain.usecases.news.GetNews
import com.example.mvvmcomposeapp2.domain.usecases.news.NewsUseCases
import com.example.mvvmcomposeapp2.domain.usecases.news.SearchNews
import com.example.mvvmcomposeapp2.domain.usecases.news.UpsertArticle
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
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            getArticles = GetArticles(newsDao),
            getArticle = GetArticle(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = "news_db"
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}