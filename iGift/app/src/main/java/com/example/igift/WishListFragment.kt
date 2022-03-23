package com.example.igift

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.igift.adapters.ProductListAdapter
import com.example.igift.data.Datasource
import com.example.igift.databinding.FragmentWishListBinding
import com.example.igift.model.Product



class WishListFragment : Fragment() {

    private var _binding: FragmentWishListBinding? = null
    private var _productList: List<Product>? = null
    private var _wishListTitle: String? = null;

    private val binding get() = _binding!!
    private val productList get() = _productList!!
    private val wishListTitle get() = _wishListTitle!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWishListBinding.inflate(inflater, container, false)
        _productList = Datasource().loadProducts()
        Log.v("Test",_productList.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val wishListTitle = binding.wishListTitle
        val recylerProductList : RecyclerView = binding.wishlistRecyclerView

        wishListTitle.text = "This is the wishList"
        recylerProductList.adapter = ProductListAdapter(requireContext(), productList)
        recylerProductList.setHasFixedSize(true)
    }


}