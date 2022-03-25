package com.example.igift.model

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot

class Product(val name: String, val category: String, val price: Double, val imageUrl: String) {

    companion object {
        fun DocumentSnapshot.toProduct(): Product? {
            try {
                val name = getString("name")!!
                val category = getString("category")!!
                val price = getDouble("current_price")!!
                val url = getString("image_url")!!
                return Product(name,category, price, url)
            } catch (e: Exception) {
                Log.e(TAG, "Error converting user profile", e)
                FirebaseCrashlytics.getInstance().log("Error converting user profile")
                FirebaseCrashlytics.getInstance().setCustomKey("userId", id)
                FirebaseCrashlytics.getInstance().recordException(e)
                return null
            }
        }
        private const val TAG = "Product"
    }

    override fun toString(): String {
        return name + " - "+ category
    }
}