package com.moyehics.movieapp.util

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.snackBar(msg:String){
    Snackbar.make(requireView(),msg,Snackbar.LENGTH_LONG).show()
}