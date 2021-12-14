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







class MovieListAdapter(_context: Context,_movieList:List<SocialAppUser>):RecyclerView.Adapter<MovieListAdapter.viewHolder>() {

    private var movieList: List<SocialAppUser>? = _movieList
    var context = _context


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

        // Click Event

        val tempObj = movieList?.get(position)

        holder.imageView.setOnClickListener{
//            val intent = Intent(context,New_Activity::class.java)
//            if (tempObj != null) {
//                intent.putExtra("IMAGE", tempObj.imageUrl)
//                intent.putExtra("COMMENTS", Gson().toJson(tempObj.comments))
//                intent.putExtra("ID",tempObj.db_id)
//
//                Log.d("OBJECT","${tempObj.db_id}")
//                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
//                context.startActivity(intent)
//            }
            val activity = it.getContext() as AppCompatActivity
            val bundle = Bundle()
            bundle.putString("IMAGE", tempObj!!.imageUrl)
            bundle.putString("COMMENTS", Gson().toJson(tempObj.comments))
            bundle.putInt("ID",tempObj.db_id)

            val singleItemFragment = SingleItemFragment()
            singleItemFragment.arguments = bundle

            activity.supportFragmentManager.beginTransaction().replace(R.id.layout,singleItemFragment).addToBackStack(null).commit()

        }

    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    fun setMovieList(movieList: List<SocialAppUser>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }


}

