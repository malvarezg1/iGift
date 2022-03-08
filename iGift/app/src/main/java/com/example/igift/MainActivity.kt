package com.example.igift

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.igift.adapters.OccasionAdapter
import com.example.igift.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val occasionData = Datasource().loadOcasions()
        val recyclerOccasions = findViewById<RecyclerView>(R.id.occacionsRecyclerView)
        recyclerOccasions.adapter = OccasionAdapter(this,occasionData)
        recyclerOccasions.setHasFixedSize(true)
    }
}