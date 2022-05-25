package com.example.igift.data

import android.content.Context
import android.util.Log
import androidx.core.graphics.scaleMatrix
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.igift.model.Product
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.LifecycleCoroutineScope
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope

object PropertiesManager {


    private lateinit var dataStore: DataStore<Preferences>

    fun createWishListStorage(context : Context, list : MutableList<Product>){
        dataStore = context.createDataStore(name = "wishList")
        GlobalScope.launch(Dispatchers.IO) {
            val jsonString = Gson().toJson(list).toString()
            save("wishList", jsonString)
        }
    }

    //Local Storage
    fun saveOnWishList(product: Product){
        GlobalScope.launch(Dispatchers.IO) {
            val list = getWhishList()
            list.add(product)
            val jsonString= Gson().toJson(list).toString()
            save("wishList", jsonString)
            Log.v("JSON", "Saved on WL ")
        }
    }

    suspend fun getWhishList(): MutableList<Product>{
        val value = read("wishList")
        Log.v("JSON", "recuperado" + value)
        val arrayProductType = object : TypeToken<MutableList<Product>>() {}.type
        val productList : MutableList<Product> = Gson().fromJson(value, arrayProductType)
        Log.v("JSON", "retornado" + productList.toString())
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