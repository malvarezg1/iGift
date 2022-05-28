package com.example.igift.data

import android.util.Log
import com.example.igift.adapters.PeopleAdapter
import com.example.igift.model.User
import com.example.igift.model.User1
import java.nio.file.attribute.UserDefinedFileAttributeView

object UsersRepository {

    suspend fun getUsers(): List<User1> {
        val users = Firestore.getUsers()
        return users
    }

    suspend fun getUsersByQuery(query : String) : List<User1>{
        val users = Firestore.getUsersByQuery(query)
        return users
    }

    suspend fun getRecentSearches(){
        val users = UserSearchesPropertiesManager.recoverRecentSearchesFromLocalStorage()
    }
}