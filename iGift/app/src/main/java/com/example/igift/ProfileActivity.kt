package com.example.igift

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.igift.data.Datasource
import com.example.igift.model.User

class ProfileActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
            setContentView(R.layout.activity_main)

            var profileImageView = findViewById<ImageView>(R.id.profileImage)
            var nameImageView = findViewById<TextView>(R.id.profileName)
            var cityImageView = findViewById<TextView>(R.id.profileCity)

            val user : User = Datasource().loadUser()
            nameImageView.text = resources.getString(user.nameResourceId)
            cityImageView.text = resources.getText(user.cityResourceId)
            profileImageView.setImageResource(user.imageResouceId)

        }
}