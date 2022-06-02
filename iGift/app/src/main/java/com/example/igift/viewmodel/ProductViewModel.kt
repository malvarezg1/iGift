package com.example.igift.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igift.data.Datasource
import com.example.igift.data.Firestore
import com.example.igift.data.WishlistPropertiesManager
import com.example.igift.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel :ViewModel() {

    private val _product = MutableLiveData<Product>()
    private val _name = MutableLiveData<String>()
    private val _availability = MutableLiveData<List<Store>>()


    val product : LiveData<Product> = _product
    val name : LiveData<String> = _name
    val availability : LiveData<List<Store>> = _availability

    init {

    }

    private fun getProductById(name:String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val recProduct = Firestore.getProductByName(name)
                val recAvailability = Firestore.getAvailabilitiesByProductName(name)

                if (recProduct != null) {
                    _product.postValue(recProduct!!)
                }
                if (recAvailability != null) {
                    val lista : MutableList<Store> = mutableListOf<Store>()

                    for (item : HashMap<String,String> in recAvailability.stores as List<HashMap<String,String>>){
                        lista.add(Store(item.get("name").toString(),item.get("image_url").toString()))

                    }
                    _availability.postValue(lista)
                }
            } catch (e: Exception) {
                Log.d("EXC", e.toString())
                e.printStackTrace()
            }
        }
    }

    fun setName(value :String ){
        _name.value = value
        getProductById(name.value!!)
    }

}