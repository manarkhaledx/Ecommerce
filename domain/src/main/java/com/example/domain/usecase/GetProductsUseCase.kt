package com.example.domain.usecase

import com.example.domain.repo.ProductRepo

class GetProductsUseCase(private val productRepo: ProductRepo) {

    suspend operator fun invoke() = productRepo.getProductsFromRemote()
}