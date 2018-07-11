package com.app.chhatrasal.task1.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.app.chhatrasal.task1.R
import com.squareup.picasso.Picasso

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val imageView : ImageView =  findViewById (R.id.flag_image)
        Picasso.get()
                .load(intent.getStringExtra("url"))
                .resize(600,600)
                .centerCrop()
                .into(imageView)
    }
}
