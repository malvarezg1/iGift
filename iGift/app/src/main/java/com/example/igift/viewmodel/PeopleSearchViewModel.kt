package com.example.igift.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igift.data.Firestore
import com.example.igift.data.UsersRepository
import com.example.igift.model.Product
import com.example.igift.model.User1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PeopleSearchViewModel: ViewModel() {
    private val _persons = MutableLiveData<List<User1>>()
    val persons: LiveData<List<User1>> = _persons

    init {
        getUsersList()
    }

     fun getUsersList(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                _persons.postValue(UsersRepository.getUsers())
            } catch (e: Exception) {
                _persons.postValue(listOf())
                Log.v("USERS", e.toString())
            }

        }
    }

}