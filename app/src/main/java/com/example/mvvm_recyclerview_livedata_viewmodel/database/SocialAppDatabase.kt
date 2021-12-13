package com.example.mvvm_recyclerview_livedata_viewmodel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities=[SocialAppUser::class],version = 1, exportSchema = false)
@TypeConverters(GithubTypeConvertor::class)
abstract class SocialAppDatabase : RoomDatabase() {

    abstract fun userDao():SocialAppDao

    companion object{
        @Volatile
        private var INSTANCE: SocialAppDatabase? = null

        fun getDatabase(context: Context):SocialAppDatabase {
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SocialAppDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance

            }
        }
    }
}