package com.example.igift.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igift.data.Firestore
import com.example.igift.model.Product
import kotlinx.coroutines.launch


class WishListViewModel : ViewModel(){
    private val _products = MutableLiveData<List<Product>>()
    private val _imageUrl = MutableLiveData<String>()

    val products : LiveData<List<Product>> = _products


    init {
        Log.v("LOG", "Viewmodel created")
        getWishListProducts()
    }

    private fun getWishListProducts() {
        viewModelScope.launch{
            try {
                _products.value =  Firestore.getProducts("accessories")
            }
            catch(e : Exception){
                _products.value =  listOf()
            }
        }
    }
}