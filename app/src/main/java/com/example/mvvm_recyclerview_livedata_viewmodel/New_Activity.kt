package com.example.mvvm_recyclerview_livedata_viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class New_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val imageView = findViewById<ImageView>(R.id.imageView2)
        val textView = findViewById<TextView>(R.id.textView2)

        textView.text = intent.getStringExtra("ID")
        Glide
            .with(this)
            .load(intent.getStringExtra("IMAGE"))
            .fitCenter()
            .circleCrop()
//            .apply(RequestOptions().override(600, 550))
            .into(imageView)
    }
}