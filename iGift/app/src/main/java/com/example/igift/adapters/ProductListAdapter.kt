package com.example.igift.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.igift.databinding.ProductListItemBinding
import com.example.igift.model.Product
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.igift.data.PropertiesManager

class ProductListAdapter( val context : Context) : ListAdapter<Product,ProductListAdapter.ProductViewHolder>(DiffCallback) {


    class ProductViewHolder (private var binding : ProductListItemBinding, val context: Context) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.productListNameImageView.text = product.name
            binding.productListBrandImageView.text = product.category
            binding.productListPriceImageViewtext.text = product.price.toString()
            val imgUri = product.imageUrl.toUri().buildUpon().scheme("https").build()

            Glide.with(binding.root)
                .load(imgUri)
                .into(binding.productListImageView)
        }

        fun setListener(context: Context, item: Product?) {
            binding.heart.setOnClickListener {
                PropertiesManager.saveOnWishList(context, item!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductListItemBinding.inflate(LayoutInflater.from(parent.context)), context)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.setListener(context, item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.name == newItem.name
        }
    }
}