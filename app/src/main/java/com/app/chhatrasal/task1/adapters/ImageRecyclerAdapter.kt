package com.app.chhatrasal.task1.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.chhatrasal.task1.R
import com.app.chhatrasal.task1.activities.ImageActivity
import com.app.chhatrasal.task1.models.WorldPopulation
import com.squareup.picasso.Picasso

class ImageRecyclerAdapter(private val list: List<WorldPopulation>, private val context: Context) : RecyclerView.Adapter<ImageRecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_item_view, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.countryName.text = list.get(p1).country
        Picasso.get()
                .load(Uri.parse(list.get(p1).flag))
                .resize(200, 200)
                .centerCrop().into(p0.imageView)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.flag_image)
        var countryName: TextView = view.findViewById(R.id.country_name)
        init {
            view.setOnClickListener {
                val intent : Intent = Intent(context, ImageActivity::class.java)
                intent.putExtra("url","" + list.get(adapterPosition).flag)
                context.startActivity(intent)
            }
        }
    }
}