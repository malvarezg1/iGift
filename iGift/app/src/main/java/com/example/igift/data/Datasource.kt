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
            "Jewelry" ->  Recommendation(
                "Jewelry",
                "https://www.brides.com/thmb/cNiSduDNgTNCIalEeeemuBKy7OE=/500x350/filters:no_upscale():max_bytes(150000):strip_icc()/image_96ebd88f-e69d-4006-a7c2-9d8ce1d10042_1024x10242x-1fc62a5b3b7746059430a91830cc0a44.jpeg"
            )
            "Kids" ->  Recommendation(
                "Kids", "https://www.eatright.org/-/media/eatrightimages/health/weightloss/yourhealthandyourweight/promote-positive-body-image-kids-839295596.jpg?h=450&w=600&la=en&hash=F093A0879214332FA20EE690179010C4580D75AA")
            "Men" ->  Recommendation(
                "Men",
                "https://image.shutterstock.com/image-photo/mens-clothing-set-boots-watch-260nw-1427016581.jpg"
            )
            "Products" ->  Recommendation(
                "Products",
                "https://i.insider.com/5ed95c393f7370198527eea3?width=700")
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