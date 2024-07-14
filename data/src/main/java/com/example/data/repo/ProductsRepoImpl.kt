package com.example.data.repo

import com.example.data.remote.ApiService
import com.example.domain.entity.ProductsResponse
import com.example.domain.repo.ProductRepo

class ProductsRepoImpl (private val apiService: ApiService): ProductRepo {
    override suspend fun getProductsFromRemote(): ProductsResponse = apiService.getProducts()
}