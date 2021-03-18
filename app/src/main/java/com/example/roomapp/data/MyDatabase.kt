package com.example.roomapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapp.model.User

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class MyDatabase :RoomDatabase(){

    abstract val userDao:UserDao

    companion object{
        @Volatile
        private var INSTANCE :MyDatabase? = null

       /* fun getInstance_version_one(context: Context):MyDatabase{
            if(INSTANCE!=null){
                return INSTANCE as MyDatabase
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "room_app_database"
                ).build()
                INSTANCE = instance

                return INSTANCE as MyDatabase
            }
        }*/

        fun getInstance(context: Context):MyDatabase{
        /*   val tempinsta = INSTANCE
            if(tempinsta!=null)
                return tempinsta*/

            synchronized(this){
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "room_app_database"
                    ).build()
                    INSTANCE=instance
                }

                return instance
            }
        }
    }

}