package com.example.mvvm_recyclerview_livedata_viewmodel

import androidx.lifecycle.LiveData
import com.example.mvvm_recyclerview_livedata_viewmodel.database.SocialAppDao
import com.example.mvvm_recyclerview_livedata_viewmodel.database.SocialAppUser

class SocialAppRepository(val socialAppDao:SocialAppDao) {

    val readAllData : LiveData<List<SocialAppUser>> = socialAppDao.readAllData()

    suspend fun addUser(socialAppUser:SocialAppUser){
        socialAppDao.addUser(socialAppUser)
    }

    suspend fun updateLikeCount(id:Int){
        socialAppDao.updateLikeCount(id)
    }

    suspend fun updateComment(id:Int,comment:List<String>){
        socialAppDao.updateComment(id,comment)
    }

}