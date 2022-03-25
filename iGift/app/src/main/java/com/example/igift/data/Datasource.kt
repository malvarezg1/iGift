package com.example.igift.data

import android.util.Log
import com.example.igift.R
import com.example.igift.model.*
import kotlinx.coroutines.*

class Datasource {
    fun loadOcasions(): List<Occasion>{
        return listOf<Occasion>(
            Occasion(R.string.occacsionTitle1, R.drawable.ocassion1),
            Occasion(R.string.occacsionTitle2, R.drawable.ocassion2),
            Occasion(R.string.occacsionTitle3, R.drawable.occasion3),
            Occasion(R.string.occacsionTitle4, R.drawable.occasion4),
            Occasion(R.string.occacsionTitle5, R.drawable.occasion5),
        )
    }

    fun loadCategories(): List<ProductCategory>{
        return listOf<ProductCategory>(
            ProductCategory(R.string.categoryTitle1, R.drawable.electronics),
            ProductCategory(R.string.categoryTitle2,R.drawable.tshirt),
            ProductCategory(R.string.categoryTitle3,R.drawable.couch),
            ProductCategory(R.string.categoryTitle4,R.drawable.airplane),
            ProductCategory(R.string.categoryTitle5,R.drawable.dish),
        )
    }

    fun loadRecommendations() : List<Recommendation>{
        return listOf<Recommendation>(
            Recommendation("Books", "https://congreso.amecip.com/images/profile_blank.png"),
            Recommendation("Accesories", "https://congreso.amecip.com/images/profile_blank.png"),
            Recommendation("Jewelry", "https://congreso.amecip.com/images/profile_blank.png"),
        )
    }



    fun loadUser() : User {
        return User(R.string.profileName, R.string.profileCity, R.drawable.profile)
    }
}