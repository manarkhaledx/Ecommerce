package com.example.data.remote

import com.example.domain.entity.ProductsResponse
import retrofit2.http.GET

interface ApiService {

    // get only the products category shoes
    @GET("products")
    suspend fun getProducts(): ProductsResponse
}