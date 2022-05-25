package com.example.igift.data

import android.util.Log
import com.example.igift.model.User
import com.example.igift.model.User1
import java.nio.file.attribute.UserDefinedFileAttributeView

object UsersRepository {

    private val firebaseService = Firestore

    suspend fun getUsers(): List<User1> {
        val users = firebaseService.getUsers()
        return users
    }

    suspend fun getUsersByQuery(query : String) : List<User1>{
        val users = firebaseService.getUsersByQuery(query)
        return users
    }

}