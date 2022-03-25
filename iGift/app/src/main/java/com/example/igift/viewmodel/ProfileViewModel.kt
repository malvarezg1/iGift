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
        viewModelScope.launch{
            try {
                var rec_list = mutableListOf<Recommendation>()
                 val rec_user = Firestore.getUserByEmail(userEmail)
                if (rec_user != null) {
                    for (pref in rec_user.preferences.keys){
                        if(rec_user.preferences.get(pref) == true){
                            rec_list.add(Datasource().loadRecommendation(pref))
                        }
                    }
                    _user.value =  rec_user!!
                    _recommendations.value = rec_list
                }
            }
            catch(e : Exception){

            }
        }
    }

}