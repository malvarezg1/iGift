package com.example.igift.model

import android.util.Log
import com.example.igift.data.Firestore
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot

class Availability (val name : String, val stores: List<Store>){

        companion object {
            fun DocumentSnapshot.toAvailability(): Availability? {
                try {
                    val name = getString("name")!!
                    val stores = get("stores")!! as MutableList<Store>
                    return Availability(name, stores)
                } catch (e: Exception) {
                    Log.e(TAG, "Error converting availability item", e)
                    FirebaseCrashlytics.getInstance().log("Error converting availability item")
                    FirebaseCrashlytics.getInstance().setCustomKey("userId", id)
                    FirebaseCrashlytics.getInstance().recordException(e)
                    return null
                }
            }
            private const val TAG = "Availability"
        }

        override fun toString(): String {
            return name + ";" + stores
        }

}