package com.elve8valley.astutetest.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.elve8valley.astutetest.R
import com.elve8valley.astutetest.data.responses.Resource
import com.elve8valley.astutetest.databinding.ActivityMainBinding
import com.elve8valley.astutetest.ui.adapter.ImageAdapter
import com.elve8valley.astutetest.ui.viewmodels.ImageViewModel
import com.elve8valley.astutetest.util.handleApiError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),ImageAdapter.CallBack {
    private lateinit var binding : ActivityMainBinding
    private val imageViewModel : ImageViewModel by viewModels()
    private lateinit var imageAdapter: ImageAdapter
    private var layout = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolBar)
        subscribeToAllObserver()
        imageViewModel.getAllImages()
        clickListener()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val item = menu?.findItem(R.id.search)
        val searchView : SearchView =item?.actionView as SearchView
        searchView.imeOptions=EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                imageAdapter.filter.filter(p0)
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun clickListener()
    {
        binding.toolBar.setOnMenuItemClickListener { item ->
            if (item?.itemId == R.id.layout_change) {
                layout = !layout
                when {
                    layout -> binding.imageList.layoutManager =
                        LinearLayoutManager(this@MainActivity)
                    else -> binding.imageList.layoutManager = GridLayoutManager(this@MainActivity, 2)
                }
            }
            true
        }


    }

    private fun moveToDetailSceen(id:String)
    {
        startActivity(Intent(this@MainActivity,ImageDetailActivity::class.java).apply {
            putExtra("id", id)
        }

        )
    }





    private fun subscribeToAllObserver() {
        imageViewModel.imagesResponse.observe(this) {
            when (it) {
                is Resource.Success -> {
                    imageAdapter=ImageAdapter(it.value.data,this)
                    imageAdapter.setMainLis(it.value.data)
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


    private fun initRecyclerView(){
        imageAdapter.setCallBack(this)
        binding.imageList.apply {
            layoutManager= GridLayoutManager(this@MainActivity,2)
            adapter=imageAdapter
            isNestedScrollingEnabled=false

        }
    }

    override fun onViewItemClick(id: String) {
        moveToDetailSceen(id)
    }


}