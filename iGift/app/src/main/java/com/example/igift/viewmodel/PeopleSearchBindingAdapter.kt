package com.example.igift.viewmodel

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.igift.adapters.PeopleAdapter
import com.example.igift.adapters.ProductListAdapter
import com.example.igift.model.Product
import com.example.igift.model.User1

/**
 * Updates the data shown in the [RecyclerView].
*/
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<User1>?) {
    val adapter = recyclerView.adapter as PeopleAdapter
    adapter.submitList(data)
}

