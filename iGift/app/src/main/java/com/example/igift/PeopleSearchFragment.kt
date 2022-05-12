package com.example.igift

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.adapters.SearchViewBindingAdapter
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

        binding.peopleSearchRecycler.adapter = PeopleAdapter{email ->
            val profileFragment = ProfileFragment(email)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.flFragment, profileFragment)?.commit()

        }

        binding.personsSearchBarView.setOnQueryTextListener( object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.v("USERS", query!!)
                viewModel.getUsersListByQuery(query)
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.v("USERS", newText!!)
                return true
            }
        })

        Log.v("USERS", "Adapeter binded")
        return binding.root
    }

}
