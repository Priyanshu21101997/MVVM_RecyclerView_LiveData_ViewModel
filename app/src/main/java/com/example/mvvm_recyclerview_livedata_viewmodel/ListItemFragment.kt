package com.example.mvvm_recyclerview_livedata_viewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_recyclerview_livedata_viewmodel.adapter.MovieListAdapter
import com.example.mvvm_recyclerview_livedata_viewmodel.database.SocialAppDatabase
import com.example.mvvm_recyclerview_livedata_viewmodel.database.SocialAppUser
import com.example.mvvm_recyclerview_livedata_viewmodel.viewmodel.MovieViewModel


class ListItemFragment : Fragment(),MovieListAdapter.onItemCLick {

    lateinit var mAdapter: MovieListAdapter
    var mMovieList:List<SocialAppUser> = mutableListOf()
    lateinit var mViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        mViewModel.makeApiCall()
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
        mAdapter = MovieListAdapter(view.context,mMovieList,this)
        recyclerView.adapter = mAdapter

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

        return view
    }

    override fun passData(tempObj: SocialAppUser?) {
        val activity = this.context as AppCompatActivity
        val bundle = Bundle()
        bundle.putString("IMAGE", tempObj!!.imageUrl)
        bundle.putInt("ID",tempObj.db_id)

        val singleItemFragment = SingleItemFragment()
        singleItemFragment.arguments = bundle

        activity.supportFragmentManager.beginTransaction().replace(R.id.layout,singleItemFragment).addToBackStack(null).commit()

    }


}