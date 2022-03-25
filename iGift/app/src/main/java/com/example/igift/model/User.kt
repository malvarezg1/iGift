package com.example.igift.model

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.igift.R
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot

class User(
    @StringRes val nameResourceId: Int,
    @StringRes val cityResourceId: Int,
    @DrawableRes val imageResouceId: Int){

}