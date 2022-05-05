package com.example.igift.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igift.data.Firestore
import com.example.igift.model.Product
import kotlinx.coroutines.launch


class ProductViewModel : ViewModel(){
    private val _products = MutableLiveData<List<Product>>()
    private val _imageUrl = MutableLiveData<String>()
    private val _category = MutableLiveData<String>()

    val products : LiveData<List<Product>> = _products
    val category : LiveData<String> = _category

    init {
        getWishListProducts()
    }

    private fun getWishListProducts() {
        viewModelScope.launch{
            try {
                _products.value =  Firestore.getProductsByCategory("accessories")
            }
            catch(e : Exception){
                _products.value =  listOf()
            }
        }
    }
    private fun getProductByCategory(category :String){
        viewModelScope.launch {
            try {
                _products.value = Firestore.getProductsByCategory(category)
            } catch (e: Exception) {
                _products.value = listOf()
            }
        }
    }

    fun setCategory(value: String){
        if(value!= "") {
            _category.value = value
            getProductByCategory(category.value!!)
        }
    }
}