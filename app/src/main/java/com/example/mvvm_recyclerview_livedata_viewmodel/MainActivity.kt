package com.example.mvvm_recyclerview_livedata_viewmodel

import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_recyclerview_livedata_viewmodel.adapter.MovieListAdapter
import com.example.mvvm_recyclerview_livedata_viewmodel.database.SocialAppUser
import com.example.mvvm_recyclerview_livedata_viewmodel.model.MovieModel
import com.example.mvvm_recyclerview_livedata_viewmodel.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.layout,ListItemFragment()).commit()

    }
}