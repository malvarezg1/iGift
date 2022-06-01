package com.example.igift.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.igift.databinding.RecommendationItemBinding
import com.example.igift.model.Recommendation

class RecommendationsAdapter(
    private val onItemClicked: (String) -> Unit
) : ListAdapter<Recommendation, RecommendationsAdapter.RecommendationViewHolder>(DiffCallback){


    class RecommendationViewHolder(private var binding: RecommendationItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (recommendation: Recommendation) {
            binding.recommendationName.text = recommendation.name
            val imgUri = recommendation.image_url.toUri().buildUpon().scheme("https").build()
            Glide.with(binding.root)
                .load(imgUri)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.recommendationImage);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationsAdapter.RecommendationViewHolder {
        return RecommendationsAdapter.RecommendationViewHolder(
            RecommendationItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: RecommendationsAdapter.RecommendationViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener{
            onItemClicked(item.name)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Recommendation>() {
        override fun areItemsTheSame(oldItem: Recommendation, newItem: Recommendation): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Recommendation, newItem: Recommendation): Boolean {
            return oldItem.name == newItem.name
        }
    }
}