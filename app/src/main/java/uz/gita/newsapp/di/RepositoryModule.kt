package uz.gita.newsapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsapp.domain.repository.NewsRepository
import uz.gita.newsapp.domain.repository.impl.NewsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideNewsRepository(impl: NewsRepositoryImpl): NewsRepository


}