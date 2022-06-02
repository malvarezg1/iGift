package com.example.igift.viewmodel

import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.igift.adapters.RecommendationsAdapter
import com.example.igift.adapters.StoreAdapter
import com.example.igift.model.*
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Updates the data shown in the [RecyclerView].
 */


@BindingAdapter("listDataAvailability")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Store>?) {
    val adapter = recyclerView.adapter as StoreAdapter
    if (data != null) {
        Log.d("TYPE", data[0].javaClass.kotlin.toString())
        adapter.submitList(data)
    }

}


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