package com.example.igift

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

enum class ProviderType{
    BASIC
}


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val profileFragment=ProfileFragment()
        val homeFragment = HomeFragment()

        setCurrentFragment(homeFragment)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.page_1->setCurrentFragment(homeFragment)
                R.id.page_2->setCurrentFragment(homeFragment)
                R.id.page_3 -> setCurrentFragment(homeFragment)
                R.id.page_4 -> setCurrentFragment(profileFragment)
            }
            true
        }
    }


    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}