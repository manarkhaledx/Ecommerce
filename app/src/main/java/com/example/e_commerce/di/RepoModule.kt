package com.example.e_commerce.di

import com.example.data.remote.ApiService
import com.example.data.repo.ProductsRepoImpl
import com.example.domain.repo.ProductRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {

    @Provides
    fun provideRepo(apiService: ApiService): ProductRepo {
        return ProductsRepoImpl(apiService)
    }
}