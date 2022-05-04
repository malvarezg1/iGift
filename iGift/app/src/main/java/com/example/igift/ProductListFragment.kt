package com.example.igift

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.igift.adapters.ProductListAdapter
import com.example.igift.databinding.FragmentWishListBinding
import com.example.igift.viewmodel.ProductViewModel


class ProductListFragment : Fragment() {
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v("LOG", "Inflating view")
        val binding = FragmentWishListBinding.inflate(inflater, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        binding.wishlistRecyclerView.adapter =  ProductListAdapter()
        return binding.root
    }
}