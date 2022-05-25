package com.example.igift.model

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot

class Wishlist(val name : String, val products : MutableList<Product>) {

    companion object {
        fun DocumentSnapshot.toWishlist(): Wishlist? {
            try {
                val name = getString("email")!!
                val products = get("products")!! as MutableList<Product>
                return Wishlist(name, products)
            } catch (e: Exception) {
                Log.e(TAG, "Error converting Whishlist profile", e)
                FirebaseCrashlytics.getInstance().log("Error converting user profile")
                FirebaseCrashlytics.getInstance().setCustomKey("userId", id)
                FirebaseCrashlytics.getInstance().recordException(e)
                return null
            }
        }

        private const val TAG = "WishList"
    }

    override fun toString(): String {
        return name
    }
}