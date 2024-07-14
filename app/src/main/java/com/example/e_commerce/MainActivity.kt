package com.example.e_commerce

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerce.adapter.ProductAdapter
import com.example.e_commerce.databinding.ActivityMainBinding
import com.example.e_commerce.util.ItemSpacingDecoration
import com.example.e_commerce.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<ProductsViewModel>()
    private lateinit var productAdapter: ProductAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productAdapter = ProductAdapter(this)
        binding.productsRv.adapter = productAdapter
        viewModel.getProducts()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collect {
                    if (it != null) {
                        productAdapter.differ.submitList(it.products)
                    }else{
                        Log.d("MainActivity", "onCreate: Error")
                    }
                }
            }
        }

        setUpNewArrivalRV()
    }



    private fun setUpNewArrivalRV() {
        productAdapter = ProductAdapter(this)
        binding.productsRv.apply {
            val layoutManager = GridLayoutManager(
                this@MainActivity,
                2,
                GridLayoutManager.VERTICAL,
                false
            )
            binding.productsRv.layoutManager = layoutManager
            binding.productsRv.addItemDecoration(ItemSpacingDecoration(20))
            adapter = productAdapter
        }
    }

}
