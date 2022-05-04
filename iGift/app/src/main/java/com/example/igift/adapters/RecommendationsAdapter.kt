package com.example.igift.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.igift.databinding.RecommendationItemBinding
import com.example.igift.model.Recommendation

class RecommendationsAdapter(
    private val onItemClicked: () -> Unit
) : ListAdapter<Recommendation, RecommendationsAdapter.RecommendationViewHolder>(DiffCallback){



    class RecommendationViewHolder(private var binding: RecommendationItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (recommendation: Recommendation) {
            binding.recommendationName.text = recommendation.name
            val imgUri = recommendation.image_url.toUri().buildUpon().scheme("https").build()
            binding.recommendationImage.load(imgUri)
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
            Log.v("CLICK", "Aqui funciona el click")
            onItemClicked()
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