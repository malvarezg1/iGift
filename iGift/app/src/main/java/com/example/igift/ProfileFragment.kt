package com.example.igift


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.igift.adapters.RecommendationsAdapter
import com.example.igift.data.Datasource
import com.example.igift.databinding.FragmentProfileBinding
import com.example.igift.model.Recommendation
import com.example.igift.model.User


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private var _user: User? = null
    private var _recommendations: List<Recommendation>? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val user get() = _user!!
    private val recommendations get() = _recommendations!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve data and inflate the layout for this fragment
        _user = Datasource().loadUser()
        _recommendations = Datasource().loadRecommendations()
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val nameTextView = binding.profileName
        val cityTextView = binding.profileCity
        val profileImageView = binding.profileImage
        val wishListButton = binding.profileWishlisButton

        nameTextView.text = resources.getString(user.nameResourceId)
        cityTextView.text = resources.getString(user.cityResourceId)
        profileImageView.setImageResource(user.imageResouceId)

        val recommendationLayoutManager = GridLayoutManager(requireContext(), 2)
        val recyclerRecommendation: RecyclerView = binding.recommendationsRecycler
        recyclerRecommendation.layoutManager = recommendationLayoutManager
        recyclerRecommendation.adapter = RecommendationsAdapter(requireContext(), recommendations)
        recyclerRecommendation.setHasFixedSize(true)

        wishListButton.setOnClickListener {
            val wishLisFragement = WishListFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.flFragment, wishLisFragement)?.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}