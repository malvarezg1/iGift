package com.example.igift.data

import android.util.Log
import com.example.igift.model.Availability
import com.example.igift.model.Availability.Companion.toAvailability
import com.example.igift.model.Product
import com.example.igift.model.Product.Companion.toProduct
import com.example.igift.model.User1
import com.example.igift.model.User1.Companion.toUser1
import com.example.igift.model.Wishlist
import com.example.igift.model.Wishlist.Companion.toWishlist
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object Firestore {
    private val db = Firebase.firestore


    suspend fun getProductsByCategory(category: String): List<Product> {
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

    suspend fun getUserByEmail(userEmail: String): User1? {
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

    suspend fun getUsers(): List<User1> {
        return try {
            val mapNotNull =
                db.collection("users").get().await().documents.mapNotNull { it.toUser1() }
            Log.v("USERS", "En el servicio de firebase" + mapNotNull.toString())
            mapNotNull
        } catch (e: Exception) {
            Log.e("Fb Service", "Error getting users", e)
            FirebaseCrashlytics.getInstance().log("Error getting products")
            emptyList()
        }
    }

    suspend fun getUsersByQuery(query: String): List<User1> {
        return try {
            val usersRef = db.collection("users")
            val mapNotNull = usersRef.whereGreaterThanOrEqualTo("name", query)
                .whereLessThanOrEqualTo("name", query + '\uf8ff').get()
                .await().documents.mapNotNull { it.toUser1() }
            Log.v("USERS", "En el servicio de firebase" + mapNotNull.toString())
            mapNotNull
        } catch (e: Exception) {
            Log.e("Fb Service", "Error getting users", e)
            FirebaseCrashlytics.getInstance().log("Error getting products")
            emptyList()
        }
    }

    suspend fun getProductByName(name: String): Product? {
        return try {
            val product = db.collection("productos").whereEqualTo("name", name)
                .get().await().documents.mapNotNull { it.toProduct() }
            product[0]
        } catch (e: Exception) {
            Log.e("Fb Service", "Error getting product", e)
            FirebaseCrashlytics.getInstance().log("Error getting products")
            null
        }
    }


    suspend fun getWhishlist(email: String): Wishlist? {
        return try {
            Log.v("WL", email)
            val mapNotNull = db.collection("wishlists").document(email).get().await().toWishlist()
            Log.v("WL", mapNotNull.toString())
            mapNotNull
        } catch (e: Exception) {
            Log.e("WL", "Error posting whishlist", e)
            null
        }
    }

    suspend fun getAvailabilitiesByProductName(name: String) : Availability? {
        return try {
            val mapNotNull = db.collection("availability").whereEqualTo("name", name)
                .get().await().documents.mapNotNull { it.toAvailability() }
            mapNotNull[0]
        } catch (e: Exception) {
            Log.e("Fb Service", "Error getting product", e)
            FirebaseCrashlytics.getInstance().log("Error getting Availabilities of product")
            null
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





