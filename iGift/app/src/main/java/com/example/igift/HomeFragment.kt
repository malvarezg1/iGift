package com.example.igift

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.igift.adapters.CategoryAdapter
import com.example.igift.adapters.OccasionAdapter
import com.example.igift.data.Datasource
import com.example.igift.databinding.FragmentHomeBinding
import com.example.igift.model.Occasion
import com.example.igift.model.ProductCategory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var _categories: List<ProductCategory>? = null
    private var _occasions: List<Occasion>? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val categories get() = _categories!!
    private val occasions get() = _occasions!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve data and inflate the layout for this fragment
        _categories = Datasource().loadCategories()
        _occasions = Datasource().loadOcasions()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycleCategories: RecyclerView = binding.categoryRecycleView
        val categoryLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recycleCategories.layoutManager = categoryLayoutManager
        recycleCategories.adapter = CategoryAdapter(requireContext(), categories)
        recycleCategories.setHasFixedSize(true)

        val occasionsLayoutManager = GridLayoutManager(requireContext(), 2)
        val recyclerOccasions: RecyclerView = binding.occacionsRecyclerView
        recyclerOccasions.layoutManager = occasionsLayoutManager
        recyclerOccasions.adapter = OccasionAdapter(requireContext(), occasions)
        recyclerOccasions.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}