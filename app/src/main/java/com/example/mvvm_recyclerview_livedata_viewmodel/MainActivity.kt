package com.example.mvvm_recyclerview_livedata_viewmodel

import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    lateinit var mAdapter:MovieListAdapter
    var mMovieList:List<SocialAppUser> = mutableListOf()
    lateinit var mViewModel:MovieViewModel
    var apiCalled = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView =findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = GridLayoutManager(this,2)
        recyclerView.layoutManager = layoutManager
        mAdapter = MovieListAdapter(this,mMovieList)
        recyclerView.adapter = mAdapter

        mViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        mViewModel.getMovieListObserver().observe(this, Observer {
            if(it!=null) {
                mMovieList = it
                mAdapter.setMovieList(it)
            }
            else{
                findViewById<TextView>(R.id.show).visibility = View.VISIBLE
            }
        })
        if(apiCalled==false) {
            mViewModel.makeApiCall()
            apiCalled = true
        }


    }
}