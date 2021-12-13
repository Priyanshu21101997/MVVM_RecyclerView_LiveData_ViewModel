package com.example.mvvm_recyclerview_livedata_viewmodel.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SocialAppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user:SocialAppUser)

    @Query("SELECT * from user_data")
    fun readAllData(): LiveData<List<SocialAppUser>>

    @Query("UPDATE user_data SET likes = likes+1  WHERE db_id = :id")
    fun updateLikeCount(id:Int)

    @Query("UPDATE user_data SET comments=:comment WHERE db_id = :id")
    fun updateComment(id:Int,comment:List<String>)

}