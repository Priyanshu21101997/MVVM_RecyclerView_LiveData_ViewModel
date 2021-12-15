package com.example.mvvm_recyclerview_livedata_viewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvm_recyclerview_livedata_viewmodel.adapter.MovieListAdapter
import com.example.mvvm_recyclerview_livedata_viewmodel.adapter.newActivityAdapter
import com.example.mvvm_recyclerview_livedata_viewmodel.database.SocialAppUser
import com.example.mvvm_recyclerview_livedata_viewmodel.viewmodel.MovieViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SingleItemFragment : Fragment() {
    lateinit var mAdapter: newActivityAdapter
    private lateinit var mViewModel: MovieViewModel
    var mMovieList:List<SocialAppUser> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_single_item, container, false)


        val recyclerView =view.findViewById<RecyclerView>(R.id.recyclerView2)
        val bundle = this.arguments


        if (bundle != null) {
            mAdapter = newActivityAdapter(Integer.parseInt(bundle.get("ID").toString()),mMovieList)
        }
        recyclerView.adapter = mAdapter
        val layoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = layoutManager


        val imageView = view.findViewById<ImageView>(R.id.imageView2)
        val likeButton = view.findViewById<Button>(R.id.increaseLikeButton)
        val editText = view.findViewById<EditText>(R.id.editTextComment)
        val postComment = view.findViewById<Button>(R.id.postComment)


        if (bundle != null) {
            Glide
                .with(this)
                .load(bundle.get("IMAGE").toString())
                .apply(RequestOptions().override(1100, 900))
                .centerCrop()
                .into(imageView)
        }

        likeButton.setOnClickListener {
            if (bundle != null) {
                mViewModel.updateLikesCount(Integer.parseInt(bundle.get("ID").toString()))
            }
        }

        postComment.setOnClickListener {
            val postId = Integer.parseInt(bundle?.get("ID").toString())
            val comment = mViewModel.getMovieListObserver().value?.get(postId-1)?.comments as MutableList<String>
            comment.add(editText.text.toString())
            Log.d("NEWCOMMENT","$comment")
            if (bundle != null) {
                mViewModel.updateComment(postId,comment)
            }
            Toast.makeText(it.context,"Comment added successfully !!!",Toast.LENGTH_LONG).show()
            editText.text.clear()
        }

        mViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        mViewModel.getMovieListObserver().observe(viewLifecycleOwner, Observer {
            if(it!=null) {
                mMovieList = it
                mAdapter.setMovieList(it)
            }
        })

        return view
    }



}