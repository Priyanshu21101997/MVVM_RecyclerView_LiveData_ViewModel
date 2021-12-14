package com.example.mvvm_recyclerview_livedata_viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_recyclerview_livedata_viewmodel.R
import com.example.mvvm_recyclerview_livedata_viewmodel.database.SocialAppUser

class newActivityAdapter(_movieList:List<SocialAppUser>): RecyclerView.Adapter<newActivityAdapter.ViewHolder>() {

    private var movieList: List<SocialAppUser>? = _movieList

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commentsSection = itemView.findViewById<TextView>(R.id.commentTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_recycler_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(movieList?.get(position)?.comments?.size!! > 0)
            holder.commentsSection.text = movieList?.get(position)?.comments?.get(0)
        else
            holder.commentsSection.text = "No Comments Found"
    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    fun setMovieList(movieList: List<SocialAppUser>) {
        this.movieList = movieList
    }

}
