package com.example.igift.data

import android.app.SearchManager
import com.example.igift.R
import com.example.igift.model.Occasion
import com.example.igift.model.ProductCategory
import com.example.igift.model.User

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

    fun loadUser() : User {
        return User(R.string.profileName, R.string.profileCity, R.drawable.profile)
    }

}