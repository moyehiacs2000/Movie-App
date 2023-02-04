package com.moyehics.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.moyehics.movieapp.R
import com.moyehics.movieapp.data.room.entities.Movie
import com.moyehics.movieapp.databinding.FragmentMovieDetialsBinding
import com.moyehics.movieapp.util.snackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private lateinit var binding:FragmentMovieDetialsBinding
    private val args : MovieDetailsFragmentArgs by navArgs()
    private var categoryName=""
    var movie: Movie?=null
    private val viewModel:MovieViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetialsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryName = args.categoryName
        movie=args.movie
        if(movie!=null){
            binding.movieNameEditeText.setText(movie?.name)
            binding.movieDescEditeText.setText(movie?.description)
        }
        binding.icBack.setOnClickListener {
            val bundle = Bundle().apply {
                putString("categoryName", categoryName)
            }
            findNavController().navigate(R.id.action_movieDetialsFragment_to_movieListingFragment,bundle)
        }
        binding.icDone.setOnClickListener {
            val bundle = Bundle().apply {
                putString("categoryName", categoryName)
            }
            if(validation()&&movie!=null) {
                movie?.name = binding.movieNameEditeText.text.toString()
                movie?.description = binding.movieDescEditeText.text.toString()
                movie?.let { it1 -> viewModel.insertMovie(it1) }
                snackBar("Movie Updated Successfully")
                findNavController().navigate(R.id.action_movieDetialsFragment_to_movieListingFragment,bundle)
            }else if(validation()&&movie==null){
                viewModel
                    .insertMovie(
                        Movie(
                            name = binding.movieNameEditeText.text.toString(),
                            description = binding.movieDescEditeText.text.toString(),
                            movieCategoryName = categoryName
                        )
                    )
                snackBar("New Movie  Added Successfully")
                findNavController().navigate(R.id.action_movieDetialsFragment_to_movieListingFragment,bundle)
            }
        }

    }
    private fun validation():Boolean{
        var isValid = true
        if(binding.movieNameEditeText.text.toString().isBlank()){
            binding.movieNameEditeText.error="Please Enter Movie Name"
            isValid=false
        }
        if(binding.movieDescEditeText.text.toString().isBlank()){
            binding.movieDescEditeText.error="Please Enter Movie Description"
            isValid=false
        }
        return isValid
    }
}