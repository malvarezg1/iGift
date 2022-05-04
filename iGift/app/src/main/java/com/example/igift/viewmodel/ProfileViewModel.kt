package com.example.igift.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igift.data.Datasource
import com.example.igift.data.Firestore
import com.example.igift.model.Recommendation
import com.example.igift.model.User1
import kotlinx.coroutines.launch

class ProfileViewModel :ViewModel() {

    private val _user = MutableLiveData<User1>()
    private val _recommendations = MutableLiveData<List<Recommendation>>()
    private val _email = MutableLiveData<String>()

    val user : LiveData<User1> = _user
    val recommendations: LiveData<List<Recommendation>> = _recommendations
    val email : LiveData<String> = _email

    init {

    }

    private fun getUserByEmail(userEmail:String) {
        viewModelScope.launch{
            try {
                var recList = mutableListOf<Recommendation>()
                 val recUser = Firestore.getUserByEmail(userEmail)
                if (recUser != null) {
                    for (pref in recUser.preferences.keys){
                        if(recUser.preferences.get(pref) == true){
                            recList.add(Datasource().loadRecommendation(pref))
                        }
                    }
                    _user.value = recUser!!
                    _recommendations.value = recList
                }
            }
            catch(e : Exception){

            }
        }
    }
    fun setEmail(value :String ){
        _email.value = value
        getUserByEmail(email.value!!)
    }

}