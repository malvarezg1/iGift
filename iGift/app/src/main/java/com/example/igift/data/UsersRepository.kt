package com.example.igift.data

import android.util.Log
import com.example.igift.model.User
import com.example.igift.model.User1
import java.nio.file.attribute.UserDefinedFileAttributeView

object UsersRepository {

    private val firebaseService = Firestore

    suspend fun getUsers(): List<User1> {
        val users = firebaseService.getUsers()
        Log.v("USERS", users.toString())
        return users
    }

    suspend fun getUsersByQuery(query : String) : List<User1>{
        val users = firebaseService.getUsersByQuery(query)
        Log.v("USERS", users.toString())
        return users
    }

}