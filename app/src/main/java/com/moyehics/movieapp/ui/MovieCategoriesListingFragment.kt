package com.moyehics.movieapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.moyehics.movieapp.databinding.FragmentMovieCategoriesListingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieCategoriesListingFragment : Fragment() {
    val TAG:String="MovieCategoriesListingFragment"
    lateinit var binding: FragmentMovieCategoriesListingBinding
    val viewModel:MovieViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentMovieCategoriesListingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieCategories()
        viewModel.movieCategories.observe(viewLifecycleOwner){
            Log.d(TAG,it.toString())
        }
    }
}