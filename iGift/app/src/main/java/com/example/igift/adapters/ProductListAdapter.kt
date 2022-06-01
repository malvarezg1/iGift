package com.example.igift.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.igift.databinding.ProductListItemBinding
import com.example.igift.model.Product
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.igift.data.WishlistPropertiesManager

class ProductListAdapter( val context : Context,
val onClickProduct : (String) -> Unit) : ListAdapter<Product,ProductListAdapter.ProductViewHolder>(DiffCallback) {


    class ProductViewHolder (private var binding : ProductListItemBinding, val context: Context) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.productListNameImageView.text = product.name
            binding.productListBrandImageView.text = product.category
            binding.productListPriceImageViewtext.text = product.price.toString()
            val imgUri = product.imageUrl.toUri().buildUpon().scheme("https").build()

            Glide.with(binding.root)
                .load(imgUri)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.productListImageView)
        }

        fun setListener(context: Context, item: Product?) {
            binding.heart.setOnClickListener {
                WishlistPropertiesManager.saveWishListOnLocalStorage(item!!)
                Toast.makeText(context, "Product added to WishList", Toast.LENGTH_LONG).show()
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
        holder.itemView.setOnClickListener{
            onClickProduct(item.name)
        }
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