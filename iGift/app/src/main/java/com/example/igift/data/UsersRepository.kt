package com.example.igift.data

import android.util.Log
import com.example.igift.model.User1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

     fun uploadRecentSearchesToFirebase(email:String){
        GlobalScope.launch(Dispatchers.IO) {
            val list  = UserSearchesPropertiesManager.recoverRecentSearchesFromLocalStorage()
            Firestore.updateRecentSearches(email, list)
        }
    }

    suspend fun downloadSearchesFromFirebase(email : String): MutableList<User1>{
        var list = mutableListOf<User1>()
        val ul  = Firestore.getRecentSearchesList(email)
        if(ul == null){
            Firestore.postRecentSearches(email)
        }
        else{
            list = ul.searches
        }
        return list

    }
}