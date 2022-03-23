package com.example.igift.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.igift.R
import com.example.igift.model.Product

class ProductListAdapter (private val context: Context, private val dataset: List<Product>) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    class ProductViewHolder (private val view: View) : RecyclerView.ViewHolder(view){
        val productName : TextView = view.findViewById(R.id.productListNameImageView)
        val productImage: ImageView = view.findViewById(R.id.productListImageView)
        val productPrice: TextView = view.findViewById(R.id.productListPriceImageViewtext)
        val productBrand: TextView = view.findViewById(R.id.productListBrandImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ProductViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = dataset[position]
        holder.productImage.setImageResource(item.imageResouceId)
        holder.productName.text = context.resources.getString(item.nameResourceId)
        holder.productBrand.text = context.resources.getString(item.brandResourceId)
        holder.productPrice.text = context.resources.getString(item.priceResourceId)
    }

    override fun getItemCount(): Int {
      return dataset.size
    }


}