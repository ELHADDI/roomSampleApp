package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.model.User

//Contains the methods used for accessing the database
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table")
    fun readAllUser():LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE id=:id ")
    suspend fun getUserById(id:String):User

    @Update
    suspend fun updateµUser(user:User)

    @Delete
    suspend fun deleteUser(user:User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()
}