package com.example.igift.model

import android.util.Log
import com.example.igift.data.Firestore
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot

class Store (val name : String, val image_url: String){

        companion object {
            fun DocumentSnapshot.toStore(): Store? {
                try {
                    val image_url = getString("image_url")!!
                    val name = getString("name")!!
                    return Store(name, image_url)
                } catch (e: Exception) {
                    Log.e(TAG, "Error converting store item", e)
                    FirebaseCrashlytics.getInstance().log("Error converting store item")
                    FirebaseCrashlytics.getInstance().setCustomKey("userId", id)
                    FirebaseCrashlytics.getInstance().recordException(e)
                    return null
                }
            }
            private const val TAG = "Availability"
        }

        override fun toString(): String {
            return name + ";" + image_url
        }

}