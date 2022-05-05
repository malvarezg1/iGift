package com.example.igift

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.igift.data.Firestore
import com.example.igift.services.NetworkConnection
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

enum class ProviderType{
    BASIC
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Eventual connectivity
        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this, Observer { isConnected ->
            if(!isConnected){
                Log.v("CON","Is Connected")
                setContentView(R.layout.disconection)
            }
            else {
                Log.v("CON", "Is Not Connected")
                setContentView(R.layout.activity_main)
                // Views and Fragments
                val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                val personalProfileFragment=ProfileFragment(intent.getStringExtra("email").toString())
                val guestProfileFragment=ProfileFragment("lorena.ar@gmail.com")
                val homeFragment = HomeFragment()

                // Variables that come from previous Intents
                val email = intent.getStringExtra("email").toString()

                // Fragment Exchange
                setCurrentFragment(homeFragment)
                bottomNavigationView.setOnItemSelectedListener {
                    when(it.itemId){
                        R.id.page_1->setCurrentFragment(homeFragment)
                        R.id.page_2->setCurrentFragment(guestProfileFragment)
                        R.id.page_3 -> setCurrentFragment(homeFragment)
                        R.id.page_4 -> setCurrentFragment(personalProfileFragment)
                    }
                    true
                }
            }
        })
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}