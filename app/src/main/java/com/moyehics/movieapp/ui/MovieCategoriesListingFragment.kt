package com.moyehics.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moyehics.movieapp.R
import com.moyehics.movieapp.databinding.FragmentMovieCategoriesListingBinding


class MovieCategoriesListingFragment : Fragment() {
    val TAG:String="MovieCategoriesListingFragment"
    lateinit var binding: FragmentMovieCategoriesListingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentMovieCategoriesListingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}