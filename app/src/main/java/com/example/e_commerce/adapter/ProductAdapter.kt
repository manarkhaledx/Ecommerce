package com.example.e_commerce.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.Product
import com.example.e_commerce.R
import com.example.e_commerce.databinding.ProductItemBinding
import com.example.e_commerce.util.formatPrice
import com.example.e_commerce.util.formatRating
import com.example.e_commerce.util.getProductPrice

class ProductAdapter(private val context: Context) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                Glide.with(context).load(product.images[0]).into(productImage)

                productName.text = product.title

                brandPriceBeforeOffer.text = product.price.toString()

                tvRating.text = "Reviews(${product.rating.toFloat().formatRating()})"

                // Check if offerPercentage is not null before using it
                brandPriceAfterOffer.text =
                    product.discountPercentage.getProductPrice(product.price).toString()

                val newPrice = product.discountPercentage.getProductPrice(product.price)
                // Format the discounted price
                brandPriceAfterOffer.text = context.getString(R.string.egp_).plus(newPrice.toFloat().formatPrice())

                brandPriceAfterOffer.visibility = View.VISIBLE
                brandPriceBeforeOffer.paintFlags =
                    brandPriceBeforeOffer.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


            }
        }
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)

        // check if the rating is null or not
        holder.binding.ratingBar.rating = product.reviews.size.toFloat()


    }
}


