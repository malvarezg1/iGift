package com.example.igift.model

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot

class RecentSearches(val email: String, val searches: MutableList<User1>){

    companion object {
        fun DocumentSnapshot.toSearchesList(): RecentSearches? {
            try {
                val email = getString("email")!!
                val searches = get("searches")!! as MutableList<User1>
                return RecentSearches(email, searches)
            } catch (e: Exception) {
                Log.e(TAG, "Error converting Searches List", e)
                FirebaseCrashlytics.getInstance().log("Error converting user profile")
                FirebaseCrashlytics.getInstance().setCustomKey("userId", id)
                FirebaseCrashlytics.getInstance().recordException(e)
                return null
            }
        }

        private const val TAG = "RecentSearch"
    }

    override fun toString(): String {
        return email
    }
}