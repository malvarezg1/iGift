package com.example.igift.viewmodel

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.igift.adapters.RecommendationsAdapter
import com.example.igift.model.Recommendation
import com.example.igift.model.User1
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Updates the data shown in the [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Recommendation>?) {
    val adapter = recyclerView.adapter as RecommendationsAdapter
    adapter.submitList(data)
}

@BindingAdapter("profileName")
fun bindName(textView: TextView, data: User1?) {
    if (data != null) {
        Log.v("THREAD", "data.name")
        textView.text = data.name
    }
}

@BindingAdapter("profileImage")
fun bindProfileImage(imageView: CircleImageView, data: User1?) {
    val imgUri = data?.image_url?.toUri()?.buildUpon()?.scheme("https")?.build()
    imageView.load(imgUri)
}