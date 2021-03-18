package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.data.UserDao
import com.example.roomapp.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData : LiveData<List<User>> = userDao.readAllUser()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun getUserById(id:String):User{
        return userDao.getUserById(id)
    }

    suspend fun updateUser(user:User){
        return userDao.updateµUser(user)
    }

    suspend fun deleteUser(user:User){
        return userDao.deleteUser(user)
    }

    suspend fun deleteAllUser(){
        return userDao.deleteAllUser()
    }
}