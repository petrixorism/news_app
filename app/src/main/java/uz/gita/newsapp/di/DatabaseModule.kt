package uz.gita.newsapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.newsapp.data.source.local.NewsDatabase
import uz.gita.newsapp.data.source.local.dao.NewsDao

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase =
        NewsDatabase.init(context)

    @Provides
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.getNewsDao()


}