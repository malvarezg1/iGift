package com.example.igift.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igift.data.Firestore
import com.example.igift.data.WishlistPropertiesManager
import com.example.igift.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProductListViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    private val _imageUrl = MutableLiveData<String>()
    private val _category = MutableLiveData<String>()

    val products: LiveData<List<Product>> = _products
    val category: LiveData<String> = _category

    init {

    }

    private fun getWishListProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _products.postValue(Firestore.getProductsByCategory("accessories"))
            } catch (e: Exception) {
                _products.postValue(listOf())
            }
        }
    }

    private fun getProductByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _products.postValue(Firestore.getProductsByCategory(category))
            } catch (e: Exception) {
                _products.postValue(listOf())
            }
        }
    }
    fun setCategory(value: String) {
        if (value != "") {
            _category.value = value
            getProductByCategory(category.value!!)
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val list: List<Product> = WishlistPropertiesManager.recoverWishListFromLocalStorage()
                    Log.v("JSON", "List View Model" + list.toString())
                    _products.postValue(list)
                } catch (e: Exception) {
                    _products.postValue(listOf())
                }
            }

        }
    }
    /*
    fun setCategory(value: String) {
        if (value != "") {
            _category.value = value
            getProductByCategory(category.value!!)
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                val  getWishListProducts()
                    val list: List<Product> = PropertiesManager.getWhishList()
                    _products.postValue(list)
                } catch (e: Exception) {
                    _products.postValue(listOf())
                }

        getWishListProducts()
        }
    }}*/
}