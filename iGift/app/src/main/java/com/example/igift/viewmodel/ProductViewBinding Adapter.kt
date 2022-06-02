package com.example.igift.viewmodel

import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.example.igift.adapters.RecommendationsAdapter
import com.example.igift.model.Product
import com.example.igift.model.Recommendation
import com.example.igift.model.User1
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Updates the data shown in the [RecyclerView].
 */

/*
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Recommendation>?) {
    val adapter = recyclerView.adapter as RecommendationsAdapter
    adapter.submitList(data)
}
*/

@BindingAdapter("productName")
fun bindName(textView: TextView, data: Product?) {
    if (data != null) {
        Log.v("THREAD", "data.name")
        textView.text = data.name
    }
}

@BindingAdapter("productImage")
fun bindProductImage(imageView: ImageView, data: Product?) {
    val imgUri = data?.imageUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
    Log.d("URI",imgUri.toString() )
    imageView.load(imgUri)
}