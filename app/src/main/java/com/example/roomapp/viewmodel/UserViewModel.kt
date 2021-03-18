package com.example.roomapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.MyDatabase
import com.example.roomapp.model.User
import com.example.roomapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel(application: Application): AndroidViewModel(application) {

    val user: MutableLiveData<User> = MutableLiveData<User>()
    private val TAG = " vvv View Model"

    val readAllData : LiveData<List<User>>
    private val userRepository: UserRepository

    init {
        val userDao=
            MyDatabase.getInstance(application).userDao
        userRepository = UserRepository(userDao)
        readAllData=userRepository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }

    fun getUserById(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            val us = userRepository.getUserById(id)
            user.postValue(us)
        }
    }

    fun updateUser(user:User){
       // Log.d(TAG,user.toString())
        viewModelScope.launch (Dispatchers.IO){
            userRepository.updateUser(user)
        }
    }

    fun deleteUser(user:User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }
    }

    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO){
            userRepository.deleteAllUser()
        }
    }

}