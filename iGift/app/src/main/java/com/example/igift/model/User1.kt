package com.example.igift.model

import android.util.Log
import com.example.igift.data.Firestore
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot

class User1 (val name : String, val email : String,  val image_url: String ,val preferences : Map<String, Boolean>){

    companion object {
        fun DocumentSnapshot.toUser1(): User1? {
            try {
                val name = getString("name")!!
                val image_url = getString("image_url")!!
                val preferences  = get("preferences")!! as HashMap<String, Boolean>
                val email = getString("email")!!
                return User1(name, email , image_url,preferences)
            } catch (e: Exception) {
                Log.e(TAG, "Error converting user profile", e)
                FirebaseCrashlytics.getInstance().log("Error converting user profile")
                FirebaseCrashlytics.getInstance().setCustomKey("userId", id)
                FirebaseCrashlytics.getInstance().recordException(e)
                return null
            }
        }
        private const val TAG = "User1"
    }

    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        var isEqual = false
        val obj = other as User1
        if(name == obj.name){
            isEqual = true
        }
        return isEqual
    }
}