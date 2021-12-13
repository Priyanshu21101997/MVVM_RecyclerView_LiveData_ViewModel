package com.example.mvvm_recyclerview_livedata_viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvm_recyclerview_livedata_viewmodel.adapter.newActivityAdapter
import com.example.mvvm_recyclerview_livedata_viewmodel.database.SocialAppUser
import com.example.mvvm_recyclerview_livedata_viewmodel.viewmodel.MovieViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import org.w3c.dom.Text

class New_Activity : AppCompatActivity() {
    lateinit var mAdapter:newActivityAdapter
    private lateinit var mViewModel: MovieViewModel
    var mMovieList:List<SocialAppUser> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val recyclerView =findViewById<RecyclerView>(R.id.recyclerView2)

        mAdapter = newActivityAdapter(mMovieList)
        recyclerView.adapter = mAdapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val imageView = findViewById<ImageView>(R.id.imageView2)
//        val textView = findViewById<TextView>(R.id.textView2)
        val likeButton = findViewById<Button>(R.id.increaseLikeButton)
        val editText = findViewById<EditText>(R.id.editTextComment)
        val postComment = findViewById<Button>(R.id.postComment)

        Glide
            .with(this)
            .load(intent.getStringExtra("IMAGE"))
            .fitCenter()
            .apply(RequestOptions().override(1600, 1550))
            .into(imageView)

        likeButton.setOnClickListener {
            Log.d("NEWCOMMENT","${intent.getIntExtra("ID", 0)}")
            mViewModel.updateLikesCount(intent.getIntExtra("ID", 0))
        }

        postComment.setOnClickListener {

            val listType =object : TypeToken<List<String>>(){}.type
            val comment = Gson().fromJson<MutableList<String>>(intent.getStringExtra("COMMENTS"),listType)
            comment.add(editText.text.toString())
            Log.d("NEWCOMMENT","$comment")
            mViewModel.updateComment(intent.getIntExtra("ID", 0),comment)
            Toast.makeText(this,"Comment added successfully !!!",Toast.LENGTH_LONG).show()
            editText.text.clear()
        }

        mViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        mViewModel.getMovieListObserver().observe(this, Observer {
            if(it!=null) {
                mMovieList = it
                mAdapter.setMovieList(it)
            }
        })
    }
}
