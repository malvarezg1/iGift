package com.example.igift.data

import android.util.Log
import com.example.igift.model.Product
import com.example.igift.model.Product.Companion.toProduct
import com.example.igift.model.RecentSearches
import com.example.igift.model.RecentSearches.Companion.toSearchesList
import com.example.igift.model.User1
import com.example.igift.model.User1.Companion.toUser1
import com.example.igift.model.Wishlist
import com.example.igift.model.Wishlist.Companion.toWishlist
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object Firestore{
     private val db = Firebase.firestore


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

    suspend fun getUsers(): List<User1>{
        return try {
            val mapNotNull = db.collection("users").get().await().documents.mapNotNull { it.toUser1() }
            Log.v("USERS", "En el servicio de firebase" + mapNotNull.toString())
            mapNotNull
        } catch (e: Exception) {
            Log.e("Fb Service", "Error getting users", e)
            FirebaseCrashlytics.getInstance().log("Error getting products")
            emptyList()
        }
    }

    suspend fun getUsersByQuery(query : String): List<User1>{
        return try {
            val usersRef = db.collection("users")
            val mapNotNull =usersRef.whereGreaterThanOrEqualTo("name", query).whereLessThanOrEqualTo("name", query+ '\uf8ff').get().await().documents.mapNotNull { it.toUser1() }
            Log.v("USERS", "En el servicio de firebase" + mapNotNull.toString())
            mapNotNull
        } catch (e: Exception) {
            Log.e("Fb Service", "Error getting users", e)
            FirebaseCrashlytics.getInstance().log("Error getting products")
            emptyList()
        }
    }

    suspend fun getWhishlist(email:String) : Wishlist?{
        return try{
            Log.v("WL", email)
            val mapNotNull = db.collection("wishlists").document(email).get().await().toWishlist()
            Log.v("WL", mapNotNull.toString())
            mapNotNull
        }
        catch (e: Exception) {
            Log.e("WL", "Error posting whishlist", e)
            null
        }
    }

     fun postWhishlist(email: String) {
        try {
            val data = hashMapOf(
                "email" to email,
                "products" to emptyList<Product>()
            )
            val wishListRef = db.collection("wishlists").document(email)
                wishListRef.set(data).addOnSuccessListener { documentReference ->
                Log.d("WL", "DocumentSnapshot written with ID: email ")
            }
            Log.v("WL", "Saving WishList On Firebase")
        } catch (e: Exception) {
            Log.e("WL", "Error posting whishlist", e)
            FirebaseCrashlytics.getInstance().log("Error getting products")
        }
    }

    suspend fun updateWishList(email : String, list: MutableList<Product>){
        try {
            val wishList = db.collection("wishlists").document(email)
            wishList.update("products", list)
                .addOnSuccessListener { Log.d("WL", "DocumentSnapshot successfully updated!") }
        }
        catch(e : Exception){
            Log.e("WL", "Error updating whishlist", e)
            FirebaseCrashlytics.getInstance().log("Error getting products")
        }
    }

    // Methods for recen searches
    suspend fun updateRecentSearches(email : String, list: MutableList<User1>){
        try {
            val recentSearches = db.collection("recentSearches").document(email)
            recentSearches.update("searches", list)
                .addOnSuccessListener { Log.d("RS", "DocumentSnapshot successfully updated!") }
        }
        catch(e : Exception){
            Log.e("WL", "Error updating recentSearches", e)
            FirebaseCrashlytics.getInstance().log("Error getting recentSearches")
        }
    }

    suspend fun getRecentSearchesList(email:String) : RecentSearches?{
        return try{
            Log.v("RS", email)
            val mapNotNull = db.collection("recentSearches").document(email).get().await().toSearchesList()
            Log.v("RS", mapNotNull.toString())
            mapNotNull
        }
        catch (e: Exception) {
            Log.e("RS", "Error posting whishlist", e)
            null
        }
    }

    fun postRecentSearches(email: String) {
        try {
            val data = hashMapOf(
                "email" to email,
                "searches" to emptyList<Product>()
            )
            val searchesRef = db.collection("recentSearches").document(email)
            searchesRef.set(data).addOnSuccessListener { documentReference ->
                Log.d("RS", "DocumentSnapshot written with ID: email ")
            }
            Log.v("RS", "Saving WishList On Firebase")
        } catch (e: Exception) {
            Log.e("RS", "Error posting whishlist", e)
            FirebaseCrashlytics.getInstance().log("Error getting searches")
        }
    }
}

