package com.elve8valley.astutetest.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elve8valley.astutetest.R
import com.elve8valley.astutetest.data.responses.Data
import com.elve8valley.astutetest.databinding.ImageListBinding
import com.elve8valley.astutetest.util.Constants
import java.util.*
import kotlin.collections.ArrayList

class ImageAdapter(private val list : ArrayList<Data>,private val context: Context) : RecyclerView.Adapter<ImageAdapter.ViewHolder>(),Filterable{
    private val mDisplayedValues = ArrayList<Data>()
    private lateinit var callBack : CallBack
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {

            private val binding:ImageListBinding= ImageListBinding.bind(view)

            fun bind(response: Data, context: Context,callBacks: CallBack)
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

    fun setMainLis(l:ArrayList<Data>)
    {
        mDisplayedValues.addAll(l)
        notifyDataSetChanged()
    }

    fun setCallBack(callBacks: CallBack)
    {
        callBack=callBacks
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater=
            LayoutInflater.from(parent.context).inflate(R.layout.image_list,parent,false)

        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(mDisplayedValues[position],context,callBack)
    }

    override fun getItemCount(): Int {
       return mDisplayedValues.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
               val filterArrayList = ArrayList<Data>()
                if(p0?.isBlank()!! or p0?.isEmpty()!!)
                {
                    filterArrayList.addAll(list)
                }
                else
                {
                    val filterPattern = p0.toString().lowercase(Locale.ROOT)
                    list.forEach {
                        if(it.title.lowercase(Locale.ROOT).contains(filterPattern))
                        {
                            filterArrayList.add(it)
                        }
                    }
                }
                val result = FilterResults()
                result.values=filterArrayList
                return result
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                mDisplayedValues.clear()
                mDisplayedValues.addAll(p1?.values as ArrayList<Data>)
                notifyDataSetChanged()
            }

        }
    }

    interface CallBack{
        fun onViewItemClick(id:String)
    }


}