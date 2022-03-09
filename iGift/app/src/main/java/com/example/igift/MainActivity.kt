package com.example.igift

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.igift.adapters.CategoryAdapter
import com.example.igift.adapters.OccasionAdapter
import com.example.igift.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val categoriesData = Datasource().loadCategories()
        val recycleCategories = findViewById<RecyclerView>(R.id.categoryRecycleView)
        val categaroyLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recycleCategories.layoutManager = categaroyLayoutManager
        recycleCategories.adapter = CategoryAdapter(this, categoriesData)
        recycleCategories.setHasFixedSize(true)


        val occasionData = Datasource().loadOcasions()
        val recyclerOccasions = findViewById<RecyclerView>(R.id.occacionsRecyclerView)
        val occasionslayoutManager = GridLayoutManager(this, 2)

        recyclerOccasions.layoutManager = occasionslayoutManager
        recyclerOccasions.adapter = OccasionAdapter(this,occasionData)
        recyclerOccasions.setHasFixedSize(true)
    }
}