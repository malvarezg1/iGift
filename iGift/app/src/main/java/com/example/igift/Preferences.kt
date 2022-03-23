package com.example.igift

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import com.example.igift.adapters.CustomAdapter
import com.example.igift.model.DataModel
import java.util.*

class Preferences : AppCompatActivity() {
    private var dataModel: ArrayList<DataModel>? = null
    private lateinit var listView: ListView
    private lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        title = "KotlinApp"
        listView = findViewById<View>(R.id.listView) as ListView
        dataModel = ArrayList<DataModel>()
        dataModel!!.add(DataModel("Accesories", false))
        dataModel!!.add(DataModel("Bags", false))
        dataModel!!.add(DataModel("Beauty", false))
        dataModel!!.add(DataModel("House", true))
        dataModel!!.add(DataModel("Jewlery", true))
        dataModel!!.add(DataModel("Kids", true))
        dataModel!!.add(DataModel("Men", true))
        dataModel!!.add(DataModel("Shoes", false))
        dataModel!!.add(DataModel("Woman", false))
        adapter = CustomAdapter(dataModel!!, applicationContext)
        listView.adapter = adapter
        listView.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            val dataModel: DataModel = dataModel!![position] as DataModel
            dataModel.checked = !dataModel.checked

            adapter.notifyDataSetChanged()
        }
    }
}