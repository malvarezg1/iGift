package com.example.igift.data

import android.util.Log
import com.example.igift.model.Product
import com.example.igift.model.Product.Companion.toProduct
import com.example.igift.model.User1
import com.example.igift.model.User1.Companion.toUser1
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object Firestore{
    val db = Firebase.firestore


    suspend fun getProductsByCategory(category: String) : List<Product> {
        return try {
            val mapNotNull = db.collection("productos").whereEqualTo("category", category)
                .get().await().documents.mapNotNull { it.toProduct() }
            mapNotNull
        } catch (e: Exception) {
            Log.e("Fb Service", "Error getting products", e)
            FirebaseCrashlytics.getInstance().log("Error getting products")
            emptyList()
        }
    }

    suspend fun getUserByEmail(userEmail: String ): User1? {
        return try {
            var user = db.collection("users").document(userEmail).get().await().toUser1()
            Log.v("Fb Service", user.toString())
            user
        } catch (e: Exception) {
            Log.e("Fb Service", "Error getting user by email ", e)
            FirebaseCrashlytics.getInstance().log("Error getting user")
            null
        }
    }

/*
    suspend fun getPreferencesFromUser(userEmail: String ) : List<Recommendation> {
        return try {
            db.collection("users").document(userEmail).get().await()

        } catch (e: Exception) {
            Log.e("Fb Service", "Error getting user friends", e)
            FirebaseCrashlytics.getInstance().log("Error getting user friends")
        }
    }
     */
}

