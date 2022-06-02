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
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class PeopleSearchViewModel: ViewModel() {
    private val _persons = MutableLiveData<List<User1>>()
    val persons: LiveData<List<User1>> = _persons

    init {
        getRecentSearches()
    }

    fun getRecentSearches(){
        viewModelScope.launch(Dispatchers.IO ){
            try{
                Log.v("RECENT", "Lo intenta")
                val list = UsersRepository.getRecentSearches()
                Log.v("RECENT", list.toString())
                _persons.postValue(list)
            }
            catch(e: Exception){
                Log.v("RECENT", "Esta haciendo la excepcion")
                _persons.postValue(UsersRepository.getUsers())
                Log.v("RECENT", e.toString())
            }
        }
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

    fun getUsersListByQuery(query : String){
        Log.v("SEARCH","Performing search on Viewmodel")
        viewModelScope.launch(Dispatchers.IO){
            try {
                Log.v("SEARCH","Launching Thread & wait")
                _persons.postValue(UsersRepository.getUsersByQuery(query))
            } catch (e: Exception) {
                Log.v("SEARCH","En el catch")
                _persons.postValue(listOf())
            }
        }
        Log.v("SEARCH", "Despues del viewmodelScope")
    }

}