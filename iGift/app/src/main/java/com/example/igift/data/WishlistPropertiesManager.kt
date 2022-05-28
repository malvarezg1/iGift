package com.example.igift.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.igift.model.Product
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.example.igift.model.User1
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object WishlistPropertiesManager {

    private lateinit var dataStore: DataStore<Preferences>

    //Whishlist on Local Storage
    fun createWishListStorage(context : Context, list : MutableList<Product>){
        dataStore = context.createDataStore(name = "wishList")
        GlobalScope.launch(Dispatchers.IO) {
            val jsonString = Gson().toJson(list).toString()
            save("wishList", jsonString)
        }
    }

    fun saveWishListOnLocalStorage(product: Product){
        GlobalScope.launch(Dispatchers.IO) {
            val list = recoverWishListFromLocalStorage()
            list.add(product)
            val jsonString= Gson().toJson(list).toString()
            save("wishList", jsonString)
            Log.v("JSON", "Saved on WL ")
        }
    }

    suspend fun recoverWishListFromLocalStorage(): MutableList<Product>{
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