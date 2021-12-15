package com.example.mvvm_recyclerview_livedata_viewmodel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvm_recyclerview_livedata_viewmodel.R
import com.example.mvvm_recyclerview_livedata_viewmodel.SingleItemFragment
import com.example.mvvm_recyclerview_livedata_viewmodel.database.SocialAppUser
import com.google.gson.Gson
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle







class MovieListAdapter(_context: Context,_movieList:List<SocialAppUser>,_onItemCLick:onItemCLick):RecyclerView.Adapter<MovieListAdapter.viewHolder>() {

    private var movieList: List<SocialAppUser>? = _movieList
    var context = _context
    var onItemCLickRef = _onItemCLick


    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val likesCnt = itemView.findViewById<TextView>(R.id.likesCnt)
        val commentsCnt = itemView.findViewById<TextView>(R.id.commentsCnt)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.likesCnt.text = movieList?.get(position)?.likes.toString()
        holder.commentsCnt.text = movieList?.get(position)?.comments?.size.toString()


        Glide
            .with(context)
            .load(movieList?.get(position)?.imageUrl)
            .fitCenter()
            .circleCrop()
            .apply(RequestOptions().override(600, 550))
            .into(holder.imageView)

        holder.imageView.setOnClickListener{
            onItemCLickRef.passData(movieList?.get(position))
        }

    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    fun setMovieList(movieList: List<SocialAppUser>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    interface onItemCLick{
        fun passData(socialAppUser: SocialAppUser?)
    }


}

