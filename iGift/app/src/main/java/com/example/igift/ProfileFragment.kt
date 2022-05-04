package com.example.igift


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager

import com.example.igift.adapters.RecommendationsAdapter
import com.example.igift.databinding.FragmentProfileBinding
import com.example.igift.viewmodel.ProfileViewModel
class ProfileFragment(
    private val email: String
): Fragment() {

    private val viewModel: ProfileViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        viewModel.setEmail(email)
        binding.viewModel = viewModel
        val recommendationLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recommendationsRecycler.layoutManager = recommendationLayoutManager
        binding.recommendationsRecycler.adapter = RecommendationsAdapter{ category ->

            val productListFragment = ProductListFragment(category.lowercase())
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.flFragment, productListFragment)?.commit()
        }
        binding.recommendationsRecycler.setHasFixedSize(true)

        binding.profileWishlisButton.setOnClickListener {
            val wishLisFragement = ProductListFragment("")
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.flFragment, wishLisFragement)?.commit()
        }
        return binding.root
    }
}