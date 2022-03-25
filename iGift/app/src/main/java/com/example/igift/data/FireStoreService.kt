package com.example.igift.data

import android.util.Log
import com.example.igift.model.Product
import com.example.igift.model.Product.Companion.toProduct
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object Firestore{
    val db = Firebase.firestore

    suspend fun getProducts(category: String) : List<Product> {
        return try{
            val mapNotNull = db.collection("productos").whereEqualTo("category", category)
                .get().await().documents.mapNotNull { it.toProduct() }
            mapNotNull
        }
        catch (e: Exception) {
            Log.e("Fb Service", "Error getting user friends", e)
            FirebaseCrashlytics.getInstance().log("Error getting user friends")
            emptyList()
        }
    }
}

