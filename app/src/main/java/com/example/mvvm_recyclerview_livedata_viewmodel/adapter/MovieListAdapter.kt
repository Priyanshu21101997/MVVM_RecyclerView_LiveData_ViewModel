package com.example.mvvm_recyclerview_livedata_viewmodel.adapter

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvm_recyclerview_livedata_viewmodel.New_Activity
import com.example.mvvm_recyclerview_livedata_viewmodel.R
import com.example.mvvm_recyclerview_livedata_viewmodel.model.MovieModel

class MovieListAdapter(_context: Context,_movieList:List<MovieModel>):RecyclerView.Adapter<MovieListAdapter.viewHolder>() {

    private var movieList: List<MovieModel>? = _movieList
    var context = _context


    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val textView = itemView.findViewById<TextView>(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.textView.text = movieList?.get(position)?.id.toString()

        Glide
            .with(context)
            .load(movieList?.get(position)?.urls?.thumb)
            .fitCenter()
            .circleCrop()
            .apply(RequestOptions().override(600, 550))
            .into(holder.imageView)

        // Click Event

        val tempObj = movieList?.get(position)

        holder.imageView.setOnClickListener{
            val intent = Intent(context,New_Activity::class.java)
            if (tempObj != null) {
                intent.putExtra("IMAGE", tempObj.urls?.thumb)
                intent.putExtra("ID",tempObj.id)
                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }

    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    fun setMovieList(movieList: List<MovieModel>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }


}

