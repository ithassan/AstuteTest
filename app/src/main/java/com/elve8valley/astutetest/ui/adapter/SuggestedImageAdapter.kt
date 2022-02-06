package com.elve8valley.astutetest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elve8valley.astutetest.R
import com.elve8valley.astutetest.data.responses.Data
import com.elve8valley.astutetest.databinding.SuggestedImageListBinding
import com.elve8valley.astutetest.util.Constants


class SuggestedImageAdapter(private val list : ArrayList<Data>,private val context: Context) : RecyclerView.Adapter<SuggestedImageAdapter.ViewHolder>() {



    private lateinit var callBack : CallBack
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {

        private val binding: SuggestedImageListBinding = SuggestedImageListBinding.bind(view)

        fun bind(response: Data, context: Context, callBacks: CallBack)
        {

            Glide.with(context)
                .load(Constants.imageBaseUrl+response.cover_image)
                .centerCrop()
                .into(binding.image)
            binding.name.text=response.title
            binding.rating.text="Rating : "+response.rating
            binding.year.text=response.year

            binding.root.setOnClickListener {
                callBacks.onViewItemClick(response.id)

            }

        }
    }
    fun setCallBack(callBacks: CallBack)
    {
        callBack=callBacks
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=
            LayoutInflater.from(parent.context).inflate(R.layout.suggested_image_list,parent,false)

        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position],context,callBack)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface CallBack{
        fun onViewItemClick(id:String)
    }


}