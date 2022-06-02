package com.example.igift

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.igift.databinding.FragmentProductDetailBinding
import com.example.igift.databinding.FragmentProfileBinding
import com.example.igift.viewmodel.ProfileViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.example.igift.adapters.RecommendationsAdapter
import com.example.igift.adapters.StoreAdapter
import com.example.igift.viewmodel.ProductViewModel


class ProductDetailFragment (
    private val name: String
        ) : Fragment() {

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this


        viewModel.setName(name)
        binding.viewModel = viewModel

        val recommendationLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.availabilityRecyclerView.layoutManager = recommendationLayoutManager
        binding.availabilityRecyclerView.adapter = StoreAdapter()

        return binding.root
    }

}