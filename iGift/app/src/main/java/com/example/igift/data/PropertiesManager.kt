package com.example.igift.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.igift.model.Product
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.google.gson.Gson
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first

object PropertiesManager {

    lateinit var productList: MutableList<Product>
    private lateinit var dataStore: DataStore<Preferences>

    //Local Storage
    fun saveOnWishList(context: Context, product: Product){
        dataStore = context.createDataStore(name = "wishList")

        val jsonString= Gson().toJson(product).toString()
        Log.v("JSON", jsonString)
        GlobalScope.launch(Dispatchers.IO){
            save("wishList", jsonString)
            Log.v("JSON", "Saved on WL ")
        }
    }

    suspend fun getWhishList(): List<Product>{
        val value = read("wishList")
        val product : Product = Gson().fromJson(value, Product::class.java)
        productList.add(product)
        return productList
    }

    private suspend fun read(key:String): String? {
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

    private suspend fun save(key:String, value:String){
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
        Log.d("SAVED",value)
    }

}