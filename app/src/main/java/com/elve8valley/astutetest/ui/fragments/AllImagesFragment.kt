package com.elve8valley.astutetest.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.elve8valley.astutetest.base.BaseFragment
import com.elve8valley.astutetest.data.responses.Data
import com.elve8valley.astutetest.data.responses.Resource
import com.elve8valley.astutetest.databinding.AllimagesFragmentBinding
import com.elve8valley.astutetest.ui.adapter.ImageAdapter
import com.elve8valley.astutetest.ui.viewmodels.ImageViewModel
import com.elve8valley.astutetest.util.handleApiError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllImagesFragment : BaseFragment<AllimagesFragmentBinding>(AllimagesFragmentBinding ::inflate) {
        private val imageViewModel : ImageViewModel by viewModels()
    private var layout = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToAllObserver()
        imageViewModel.getAllImages()
        clickListener()

    }

    fun clickListener()
    {
        binding.changeLayoutBtn.setOnClickListener{
            layout = !layout
            when {
                layout -> binding.imageList.layoutManager = LinearLayoutManager(requireContext())
                else -> binding.imageList.layoutManager = GridLayoutManager(requireContext(),2)
            }

        }
    }

    private fun subscribeToAllObserver() {
        imageViewModel.imagesResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    initRecyclerView(it.value.data)
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
                else ->
                {
                    Toast.makeText(requireContext(), "none", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun initRecyclerView(list :List<Data>){
        binding.imageList.apply {
            layoutManager=GridLayoutManager(requireContext(),2)
            adapter=ImageAdapter(list,requireContext())
            isNestedScrollingEnabled=false

        }
    }

}