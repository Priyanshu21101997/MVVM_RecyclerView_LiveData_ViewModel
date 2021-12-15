package com.example.mvvm_recyclerview_livedata_viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_recyclerview_livedata_viewmodel.R
import com.example.mvvm_recyclerview_livedata_viewmodel.database.SocialAppUser

class newActivityAdapter(_postId:Int,_movieList:List<SocialAppUser>): RecyclerView.Adapter<newActivityAdapter.ViewHolder>() {

    private var movieList: List<SocialAppUser>? = _movieList
    private var postId = _postId
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commentsSection = itemView.findViewById<TextView>(R.id.commentTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_recycler_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(movieList!!.isNotEmpty())
            holder.commentsSection.text = movieList?.get(postId-1)?.comments?.get(position)

    }

    override fun getItemCount(): Int {
        if(movieList!!.isNotEmpty())
            return movieList?.get(postId-1)?.comments!!.size
        return 0

    }

    fun setMovieList(movieList: List<SocialAppUser>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }



}
