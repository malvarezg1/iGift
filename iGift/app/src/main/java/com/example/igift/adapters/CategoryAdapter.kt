package com.example.igift.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.igift.R
import com.example.igift.model.Occasion
import com.example.igift.model.ProductCategory

class CategoryAdapter (private val context: Context, private val dateset: List<ProductCategory>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>()  {

    class CategoryViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.categoryTitleView)
        val imageView : ImageView = view.findViewById(R.id.categoryImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = dateset[position]
        holder.imageView.setImageResource(item.imageResouceId)
        holder.textView.text = context.resources.getString(item.stringResourceId)
    }

    override fun getItemCount(): Int {
        return dateset.size
    }
}