package com.elve8valley.astutetest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elve8valley.astutetest.data.repositories.ImageRepository
import com.elve8valley.astutetest.data.responses.Data
import com.elve8valley.astutetest.data.responses.Images
import com.elve8valley.astutetest.data.responses.Resource
import com.elve8valley.astutetest.data.responses.SingleImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor (private val imageRepository: ImageRepository) : ViewModel() {

    private val _imageResponse: MutableLiveData<Resource<Images>> = MutableLiveData()
    val imagesResponse: LiveData<Resource<Images>>
        get()=_imageResponse

    private val _singleImageResponse: MutableLiveData<Resource<SingleImage>> = MutableLiveData()
    val singleImagesResponse: LiveData<Resource<SingleImage>>
        get()=_singleImageResponse

    fun getAllImages()=viewModelScope.launch {
        _imageResponse.value=Resource.loading
        _imageResponse.value= imageRepository.getAllImages()
    }

    fun getImageById(id:String)=viewModelScope.launch {
        _singleImageResponse.value=Resource.loading
        _singleImageResponse.value= imageRepository.getImageById(id)
    }

    fun getSuggestedImages(id:String)=viewModelScope.launch {
        _imageResponse.value=Resource.loading
        _imageResponse.value= imageRepository.getSuggestedImages(id)
    }


}