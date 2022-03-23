package com.example.igift.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.igift.R
import com.example.igift.model.Recommendation

class RecommendationsAdapter(private val context: Context, private val dateset: List<Recommendation>): RecyclerView.Adapter<RecommendationsAdapter.RecommendationViewHolder>()  {

    class RecommendationViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.recommendation_name)
        val imageView : ImageView = view.findViewById(R.id.recommendation_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationsAdapter.RecommendationViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.recommendation_item, parent, false)
        return RecommendationsAdapter.RecommendationViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: RecommendationsAdapter.RecommendationViewHolder, position: Int) {
        val item = dateset[position]
        holder.imageView.setImageResource(item.imageResouceId)
        holder.textView.text = context.resources.getString(item.stringResourceId)
    }

    override fun getItemCount(): Int {
        return dateset.size
    }
}