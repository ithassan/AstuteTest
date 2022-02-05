package com.elve8valley.astutetest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elve8valley.astutetest.R
import com.elve8valley.astutetest.data.responses.Data
import com.elve8valley.astutetest.databinding.ImageListBinding

class ImageAdapter(private val list : List<Data>,private val context: Context) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {

            private val binding:ImageListBinding= ImageListBinding.bind(view)

            fun bind(response: Data, context: Context)
            {

               Glide.with(context)
                    .load("http://blogswizards.com/mobile_app_assignment/"+response.cover_image)
                    .centerCrop()
                    .into(binding.image)
                binding.name.text=response.title
                binding.rating.text="Rating : "+response.rating
                binding.year.text=response.year

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=
            LayoutInflater.from(parent.context).inflate(R.layout.image_list,parent,false)

        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(list[position],context)
    }

    override fun getItemCount(): Int {
       return list.size
    }


}