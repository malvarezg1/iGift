package com.example.igift.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Product(@StringRes val nameResourceId: Int, @StringRes val brandResourceId: Int,@StringRes val priceResourceId: Int, @DrawableRes val imageResouceId: Int) {
}