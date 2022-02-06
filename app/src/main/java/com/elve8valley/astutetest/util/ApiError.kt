package com.elve8valley.astutetest.util

import android.app.Activity
import android.view.View
import com.elve8valley.astutetest.data.responses.Resource
import com.google.android.material.snackbar.Snackbar


fun View.snackBar(message:String){
     val snack= Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snack.show()
}

fun handleApiError(failure: Resource.Failure, view: View)
{
    when{
        failure.isNetWorkError -> view.snackBar("Please check your internet connection")
        failure.errorCode==401 -> {

            view.snackBar("Something went wrong")
        }
        else -> {
            val error=failure.errorBody?.string().toString()
            view.snackBar(error)
        }
    }

}