package com.example.mvvm_recyclerview_livedata_viewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_recyclerview_livedata_viewmodel.adapter.MovieListAdapter
import com.example.mvvm_recyclerview_livedata_viewmodel.database.SocialAppUser
import com.example.mvvm_recyclerview_livedata_viewmodel.viewmodel.MovieViewModel


class ListItemFragment : Fragment() {

    lateinit var mAdapter: MovieListAdapter
    var mMovieList:List<SocialAppUser> = mutableListOf()
    lateinit var mViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_item, container, false)

        val recyclerView =view.findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = GridLayoutManager(view.context,2)
        recyclerView.layoutManager = layoutManager
        mAdapter = MovieListAdapter(view.context,mMovieList)
        recyclerView.adapter = mAdapter

        mViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        mViewModel.getMovieListObserver().observe(viewLifecycleOwner, Observer {
            if(it!=null) {
                mMovieList = it
                mAdapter.setMovieList(it)
            }
            else{
                Log.d("INSIDE","here")
                view.findViewById<TextView>(R.id.show).visibility = View.VISIBLE
            }
        })

        if(mViewModel.getMovieListObserver().value?.size == null)
            mViewModel.makeApiCall()

        return view
    }


}