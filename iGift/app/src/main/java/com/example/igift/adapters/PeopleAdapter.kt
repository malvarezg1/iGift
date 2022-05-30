package com.example.igift.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.igift.R
import com.example.igift.data.UserSearchesPropertiesManager
import com.example.igift.databinding.PersonItemBinding
import com.example.igift.model.User1

class PeopleAdapter(
    private val onClickPerson : (String) -> Unit
) : ListAdapter<User1, PeopleAdapter.PersonViewHolder>(DiffCallback){

    class PersonViewHolder( private val binding : PersonItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person : User1){
            binding.personNameTextView.text = person.name
            val imgUri = person.image_url.toUri().buildUpon().scheme("https").build()
            Glide.with(binding.root)
                .load(imgUri)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.personImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleAdapter.PersonViewHolder {
        return PeopleAdapter.PersonViewHolder(PersonItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PeopleAdapter.PersonViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener{
            onClickPerson(item.email)
            UserSearchesPropertiesManager.saveRecentSearchesOnLocalStorage(item)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<User1>() {
        override fun areItemsTheSame(oldItem: User1, newItem: User1): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: User1, newItem: User1): Boolean {
            return oldItem.name == newItem.name
        }
    }


}