package com.example.mvvm_recyclerview_livedata_viewmodel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities=[SocialAppUser::class],version = 2, exportSchema = false)
@TypeConverters(GithubTypeConvertor::class)
abstract class SocialAppDatabase : RoomDatabase() {

    abstract fun userDao():SocialAppDao

    companion object{

        val migration_1_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Migration Queries
                database.execSQL("ALTER TABLE user_data ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }

        }

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
                )
                    .addMigrations(migration_1_2)
                    .build()
                INSTANCE = instance
                return instance

            }
        }
    }
}