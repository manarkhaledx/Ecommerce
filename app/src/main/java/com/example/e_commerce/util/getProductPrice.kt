package com.example.e_commerce.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


fun Double?.getProductPrice(price: Double): Double {
    if (this == null)
        return price
    return price - (price * this / 100)
}

fun Float?.formatPrice(): String{
    if (this == null)
        return "0.0"
    return String.format("%.1f", this)
}
fun Float?.formatRating(): String{
    if (this == null)
        return "0.0"
    return String.format("%.0f", this)
}

class ItemSpacingDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = space
        outRect.right = space
        outRect.top = space
        outRect.bottom = space
    }
}