package com.example.igift.viewmodel

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igift.data.Datasource
import com.example.igift.data.Firestore
import com.example.igift.model.Product
import com.example.igift.model.Recommendation
import com.example.igift.model.User
import com.example.igift.model.User1
import kotlinx.coroutines.launch

class ProfileViewModel :ViewModel() {

    private val _user = MutableLiveData<User1>()
    private val _recommendations = MutableLiveData<List<Recommendation>>()

    val user : LiveData<User1> = _user
    val recommendations: LiveData<List<Recommendation>> = _recommendations



    init {
        Log.v("LOG", "Viewmodel created")
        getUserByEmail("prueba1@gmail.com")
    }

    private fun getUserByEmail(userEmail:String) {
        _recommendations.value = Datasource().loadRecommendations()
        viewModelScope.launch{
            try {
                _user.value =  Firestore.getUserByEmail(userEmail)
            }
            catch(e : Exception){

            }
        }
    }

}