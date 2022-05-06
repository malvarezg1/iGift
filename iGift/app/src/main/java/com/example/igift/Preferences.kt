package com.example.igift

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import com.example.igift.adapters.PreferencesAdapter
import com.example.igift.model.DataModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class Preferences : AppCompatActivity() {

    private var dataModel: ArrayList<DataModel>? = null
    private lateinit var listView: ListView
    private lateinit var adapter: PreferencesAdapter

    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        title = "KotlinApp"
        listView = findViewById<View>(R.id.listView) as ListView
        dataModel = ArrayList<DataModel>()
        dataModel!!.add(DataModel("Accessories", false))
        dataModel!!.add(DataModel("Bags", false))
        dataModel!!.add(DataModel("Beauty", false))
        dataModel!!.add(DataModel("House", false))
        dataModel!!.add(DataModel("Jewelry", false))
        dataModel!!.add(DataModel("Kids", false))
        dataModel!!.add(DataModel("Men", false))
        dataModel!!.add(DataModel("Products", false))
        dataModel!!.add(DataModel("Shoes", false))
        dataModel!!.add(DataModel("Woman", false))

        adapter = PreferencesAdapter(dataModel!!, applicationContext)
        listView.adapter = adapter
        listView.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            val dataModel: DataModel = dataModel!![position] as DataModel
            dataModel.checked = !dataModel.checked
            adapter.notifyDataSetChanged()
        }

        val button = findViewById<Button>(R.id.testButton)
        val email = intent.getStringExtra("email").toString()
        val name = intent.getStringExtra("name").toString()

        button.setOnClickListener{
            val hashMap = HashMap<String,Any>()
            val auxMap = HashMap<String,Boolean>()
            hashMap.put("name", name)
            for(i in 0 until listView.getCount()){
                val preference = listView.getChildAt(i).findViewById<TextView>(R.id.txtName).text
                val checkbox = listView.getChildAt(i).findViewById<CheckBox>(R.id.checkBox).isChecked

                auxMap.put(preference.toString(),checkbox)
            }
            hashMap.put("image_url", "https://congreso.amecip.com/images/profile_blank.png")
            hashMap.put("preferences", auxMap)
            db.collection("users").document(email).set(
                hashMap
            )
            showHome(email)
        }
    }

    private fun showHome(email: String){
        val homeIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }
}