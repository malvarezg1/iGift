package com.example.igift.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igift.data.Datasource
import com.example.igift.data.Firestore
<<<<<<< HEAD
=======
import com.example.igift.data.WishlistPropertiesManager
>>>>>>> 1ca790dae7d2f01f26b0a1b68667e57841e551cc
import com.example.igift.model.Product
import com.example.igift.model.Recommendation
import com.example.igift.model.User1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel :ViewModel() {

    private val _product = MutableLiveData<Product>()
    private val _name = MutableLiveData<String>()

    val product : LiveData<Product> = _product
    val name : LiveData<String> = _name

    init {

    }

    private fun getProductById(name:String) {
        viewModelScope.launch(Dispatchers.IO){
            try {

                val recProduct = Firestore.getProductByName(name)
                Log.d("RECPRODUCT", recProduct.toString())
                if (recProduct != null) {

<<<<<<< HEAD
                    _product.postValue(recProduct!!)
                }
            }
            catch(e : Exception) {

            }
=======
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

>>>>>>> 1ca790dae7d2f01f26b0a1b68667e57841e551cc
        }
    }


    fun setName(value :String ){
        _name.value = value
        getProductById(name.value!!)
    }

}