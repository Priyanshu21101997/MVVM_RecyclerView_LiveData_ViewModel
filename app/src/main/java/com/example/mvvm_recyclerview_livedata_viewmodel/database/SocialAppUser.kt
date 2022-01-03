package com.example.mvvm_recyclerview_livedata_viewmodel.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.mvvm_recyclerview_livedata_viewmodel.model.Urls
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity(tableName = "user_data")

data class SocialAppUser(@PrimaryKey(autoGenerate = true) val db_id:Int,@SerializedName("urls") val imageUrl:String?, val likes:Int,@TypeConverters(GithubTypeConvertor::class
)
val comments:List<String>?,
val isActive:Int) {

}

class GithubTypeConvertor{
    @TypeConverter
    fun fromString(value:String?):List<String>{
        val listType =object :TypeToken<ArrayList<String>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list:List<String?>):String{
        return Gson().toJson(list)
    }
}
