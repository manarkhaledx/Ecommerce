package com.example.domain.repo

import com.example.domain.entity.ProductsResponse

interface ProductRepo {
   suspend fun getProductsFromRemote(): ProductsResponse
}