package com.example.e_commerce.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Product
import com.example.domain.entity.ProductsResponse
import com.example.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
):ViewModel(){

    private val _products : MutableStateFlow<ProductsResponse?> = MutableStateFlow(null)
    val products = _products.asStateFlow()


    /**
     * Fetches products and updates the [_products] state.
     *
     * This function launches a coroutine to handle the asynchronous operation of fetching products.
     * If successful, it updates the [_products] state with the fetched products.
     * In case of an exception, it logs the error message to the console.
     */
    fun getProducts(){
        viewModelScope.launch {
            try {
              _products.value =   getProductsUseCase()

            }catch (e:Exception){
               Log.e("ProductsViewModel",e.message.toString())
            }
        }

    }
}