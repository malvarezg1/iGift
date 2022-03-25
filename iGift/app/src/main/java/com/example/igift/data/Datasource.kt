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

    fun loadRecommendation(key : String) :Recommendation{
        return when (key) {
            "Accesories" ->  Recommendation(
                "Accesories",
                "https://media.istockphoto.com/photos/mens-accessories-organized-on-table-in-knolling-arrangement-picture-id638385938?k=20&m=638385938&s=170667a&w=0&h=bHgK7MYqeqQXhHbt41xNMchDJ5a2CCxKBdU4_xIV8iQ="
            )
            "Bags" ->  Recommendation(
                "Bags",
                "https://wwd.com/wp-content/uploads/2022/02/best-deisgner-tote-bags.jpg"
            )
            "Beauty" ->  Recommendation(
                "Beauty",
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/beauty-products-1603140461.jpg?crop=0.502xw:1.00xh;0.250xw,0&resize=640:*"
            )
            "House" ->  Recommendation(
                "House",
                "https://hgtvhome.sndimg.com/content/dam/images/hgtv/fullset/2019/2/20/0/RX_HGMAG068_WowMarch-07.jpg.rend.hgtvcom.616.462.suffix/1550670129800.jpeg"
            )
            "Jewlery" ->  Recommendation(
                "Jewlery",
                "https://www.brides.com/thmb/cNiSduDNgTNCIalEeeemuBKy7OE=/500x350/filters:no_upscale():max_bytes(150000):strip_icc()/image_96ebd88f-e69d-4006-a7c2-9d8ce1d10042_1024x10242x-1fc62a5b3b7746059430a91830cc0a44.jpeg"
            )
            "Kids" ->  Recommendation(
                "Kids",
                "data:image/jpeg;base64,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"
            )
            "Men" ->  Recommendation(
                "Men",
                "https://image.shutterstock.com/image-photo/mens-clothing-set-boots-watch-260nw-1427016581.jpg"
            )
            "Products" ->  Recommendation(
                "Products",
                ""
            )
            "Shoes" ->  Recommendation(
                "Shoes",
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/lululemon-shoes-review-sneakers-1647907220.jpg"
            )
            "Woman" ->  Recommendation(
                "Woman",
                "https://static.independent.co.uk/2022/03/18/17/Best%20womens%20fleeces%20copy.jpg?quality=50&width=640&auto=webp"
            )
            else -> {
                Recommendation(
                    "Unknown",
                    "https://congreso.amecip.com/images/profile_blank.png"
                )
            }
        }
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