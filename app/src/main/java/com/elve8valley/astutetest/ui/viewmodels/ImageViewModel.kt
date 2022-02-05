package com.elve8valley.astutetest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elve8valley.astutetest.data.repositories.ImageRepository
import com.elve8valley.astutetest.data.responses.Images
import com.elve8valley.astutetest.data.responses.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor (private val imageRepository: ImageRepository) : ViewModel() {

    private val _imageResponse: MutableLiveData<Resource<Images>> = MutableLiveData()
    val imagesResponse: LiveData<Resource<Images>>
        get()=_imageResponse

    fun getAllImages()=viewModelScope.launch {
        _imageResponse.value=Resource.loading
        _imageResponse.value= imageRepository.getAllImages()
    }

}