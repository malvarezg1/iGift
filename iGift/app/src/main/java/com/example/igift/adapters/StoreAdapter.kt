package com.example.igift.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.igift.databinding.AvailabilityItemBinding
import com.example.igift.model.Store

class StoreAdapter : ListAdapter<Store, StoreAdapter.StoreViewHolder>(DiffCallback)
{
    class StoreViewHolder(private var binding: AvailabilityItemBinding ): RecyclerView.ViewHolder(binding.root) {
        fun bind (store: Store) {
            val imgUri = store.image_url.toUri().buildUpon().scheme("https").build()

            Glide.with(binding.root)
                .load(imgUri)
                .diskCacheStrategy(DiskCacheStrategy.NONE) // Esto solo lo guarda en memoria y no disco
                .into(binding.availabilityImageView);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreAdapter.StoreViewHolder {
        return StoreAdapter.StoreViewHolder(
            AvailabilityItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: StoreAdapter.StoreViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Store>() {
        override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
