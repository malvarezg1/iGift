package com.example.igift.data

import android.util.Log
import android.util.LruCache
import com.example.igift.model.User1

class RecentSearchesCache{

    private val lru: LruCache<String, User1> = LruCache( 1024* 1024)

    fun savePersonInCache(key :String, value : User1){
        try{
            lru.put(key,value)
        }
        catch(e : Exception){
            Log.v("LRU", e.toString())
        }
    }

    fun retrievePersonFromCache(key:String): User1? {
        try{
           return lru.get(key) as User1
        }
        catch(e : Exception){
            Log.v("LRU", e.toString())
        }
        return null
    }
}