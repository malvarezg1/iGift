package com.example.igift

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.igift.adapters.PeopleAdapter
import com.example.igift.databinding.FragmentPeopleSearchBinding
import com.example.igift.databinding.FragmentWishListBinding
import com.example.igift.viewmodel.PeopleSearchViewModel
import com.example.igift.viewmodel.ProductViewModel

class PeopleSearchFragment : Fragment() {
    private val viewModel: PeopleSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("USERS", "Search fragement created")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPeopleSearchBinding.inflate(inflater, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        binding.peopleSearchRecycler.adapter = PeopleAdapter()
        Log.v("USERS", "Adapeter binded")
        return binding.root
    }


}