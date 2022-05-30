package com.example.igift.data

import android.util.Log
import com.example.igift.model.User1

object UsersRepository {

    suspend fun getUsers(): List<User1> {
        val users = Firestore.getUsers()
        return users
    }

    suspend fun getUsersByQuery(query: String): List<User1> {
        Log.v("SEARCH", "Performing Query")
        return Firestore.getUsersByQuery(query)
    }

    suspend fun getRecentSearches() : MutableList<User1>{
        Log.v("RECENT", "Va a buscar al properties")
        var list = UserSearchesPropertiesManager.recoverRecentSearchesFromLocalStorage()
        if(list.isEmpty()){
            list = getUsers().toMutableList()
        }
        return list
    }
}