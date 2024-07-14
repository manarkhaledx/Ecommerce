package com.example.e_commerce.di

import com.example.domain.repo.ProductRepo
import com.example.domain.usecase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideUseCase(productRepo: ProductRepo): GetProductsUseCase {
        return GetProductsUseCase(productRepo)
    }
}