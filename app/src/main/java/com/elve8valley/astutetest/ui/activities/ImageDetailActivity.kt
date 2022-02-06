package com.elve8valley.astutetest.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.elve8valley.astutetest.data.responses.Data
import com.elve8valley.astutetest.data.responses.Resource
import com.elve8valley.astutetest.databinding.ImageDetailActivityBinding
import com.elve8valley.astutetest.ui.adapter.SuggestedImageAdapter
import com.elve8valley.astutetest.ui.viewmodels.ImageViewModel
import com.elve8valley.astutetest.util.Constants
import com.elve8valley.astutetest.util.handleApiError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailActivity :AppCompatActivity(),SuggestedImageAdapter.CallBack {
    private lateinit var binding: ImageDetailActivityBinding
    private val imageViewModel : ImageViewModel by viewModels()
    private lateinit var imageAdapter: SuggestedImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ImageDetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        subscribeToAllObserver()
        val id = intent.extras?.get("id").toString()
        imageViewModel.getImageById(id)
        imageViewModel.getSuggestedImages(id)
    }
    private fun subscribeToAllObserver() {
        imageViewModel.singleImagesResponse.observe(this) {
            when (it) {
                is Resource.Success -> {
                    updateUI(it.value.data)
                }
                is Resource.Failure -> {
                    handleApiError(it, binding.root)
                }
                else ->
                {

                }
            }
        }
        imageViewModel.imagesResponse.observe(this) {
            when (it) {
                is Resource.Success -> {
                    imageAdapter= SuggestedImageAdapter(it.value.data,this)
                    initRecyclerView()
                }
                is Resource.Failure -> {
                    handleApiError(it, binding.root)
                }
                else ->
                {

                }
            }
        }
    }

    private fun updateUI(data: Data)
    {
     binding.name.text=data.title
     binding.desc.text=data.summary
        binding.year.text=data.year
     binding.rating.text="Rating :"+data.rating
        Glide.with(this)
            .load(Constants.imageBaseUrl+data.cover_image)
            .centerCrop()
            .into(binding.image)
    }
    private fun initRecyclerView(){
        imageAdapter.setCallBack(this)
        binding.imageList.apply {
            layoutManager= LinearLayoutManager(this@ImageDetailActivity,LinearLayoutManager.HORIZONTAL, false)
            adapter=imageAdapter
            isNestedScrollingEnabled=false

        }
    }

    override fun onViewItemClick(id: String) {
        imageViewModel.getImageById(id)
        imageViewModel.getSuggestedImages(id)
    }

}