package com.example.igift.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.example.igift.model.Product
import com.example.igift.model.User1
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

object UserSearchesPropertiesManager {

    private lateinit var dataStore: DataStore<Preferences>

    // Recent Searches on Local Storage
    fun createRecentSearchesStorage(context : Context){
        dataStore = context.createDataStore(name = "recentUserSearches")
    }

    fun saveRecentSearchesOnLocalStorage(user: User1){
        GlobalScope.launch(Dispatchers.IO) {
            val list = recoverRecentSearchesFromLocalStorage()
            list.add(user)
            val jsonString= Gson().toJson(list).toString()
            save("recentUserSearches", jsonString)
            Log.v("JSON", "Saved on RS ")
        }
    }

    suspend fun recoverRecentSearchesFromLocalStorage() : MutableList<User1>{
        val value = read("recentUserSearches")
        Log.v("JSON", "recuperado" + value)
        val arrayProductType = object : TypeToken<MutableList<User1>>() {}.type
        val usersList : MutableList<User1> = Gson().fromJson(value, arrayProductType)
        Log.v("JSON", "retornado" + usersList.toString())
        return usersList
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