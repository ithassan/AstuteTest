package com.elve8valley.astutetest.util

import android.view.View
import androidx.fragment.app.Fragment
import com.elve8valley.astutetest.data.responses.Resource
import com.google.android.material.snackbar.Snackbar

fun Fragment.handleApiError(failure: Resource.Failure)
{
    when{
        failure.isNetWorkError -> requireView().snackBar("Please check your internet connection")
        failure.errorCode==401 -> {

                requireView().snackBar("Something went wrong")
        }
        else -> {
            val error=failure.errorBody?.string().toString()
            requireView().snackBar(error)
        }
    }
}

fun View.snackBar(message:String){
     val snack= Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snack.show()
}