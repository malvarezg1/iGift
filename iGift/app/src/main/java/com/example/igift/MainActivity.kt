package com.example.igift

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.igift.data.Firestore
import com.example.igift.data.PropertiesManager
import com.example.igift.data.WishlistRepository
import com.example.igift.services.NetworkConnection
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
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
                Toast.makeText(applicationContext,"Connection Lost", Toast.LENGTH_SHORT).show()

                setContentView(R.layout.disconection)
            }
            else {
                Log.v("CON", "Is Not Connected")
                Toast.makeText(applicationContext,"Connected",Toast.LENGTH_SHORT).show()

                setContentView(R.layout.activity_main)

                // Wish List Local Storage
                lifecycleScope.launch(Dispatchers.IO){
                    val wishlist = WishlistRepository.downloadListFromFirebase(intent.getStringExtra("email").toString())
                    Log.v("WL",  "Este :"+wishlist.toString())
                    PropertiesManager.createWishListStorage(applicationContext, wishlist.toMutableList())
                }

                // Views and Fragments
                val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                val personalProfileFragment=ProfileFragment(intent.getStringExtra("email").toString())
                val guestProfileFragment=ProfileFragment("martin@gmail.com")
                val peopleSearchFragment = PeopleSearchFragment()
                val homeFragment = HomeFragment()

                // Variables that come from previous Intents
                val email = intent.getStringExtra("email").toString()

                // Fragment Exchange
                setCurrentFragment(homeFragment)
                bottomNavigationView.setOnItemSelectedListener {
                    when(it.itemId){
                        R.id.page_1->setCurrentFragment(homeFragment)
                        R.id.page_2->setCurrentFragment(peopleSearchFragment)
                        R.id.page_3 -> setCurrentFragment(homeFragment)
                        R.id.page_4 -> setCurrentFragment(personalProfileFragment)
                    }
                    true
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        WishlistRepository.uploadWishListToFirebase(intent.getStringExtra("email").toString())
    }


    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}