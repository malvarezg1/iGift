package com.example.igift.data

import android.app.SearchManager
import com.example.igift.R
import com.example.igift.model.Occasion

class Datasource {

    fun loadOcasions(): List<Occasion>{
        return listOf<Occasion>(
        Occasion(R.string.occacsionTitle1, ), Occasion(R.string.occacsionTitle1)

        )
    }
}