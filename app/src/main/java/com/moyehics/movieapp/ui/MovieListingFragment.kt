package com.moyehics.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.moyehics.movieapp.R
import com.moyehics.movieapp.adapter.MovieCategoryAdapter
import com.moyehics.movieapp.adapter.MoviesAdapter
import com.moyehics.movieapp.data.room.entities.Movie
import com.moyehics.movieapp.databinding.FragmentMovieListingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListingFragment : Fragment() {
    val TAG:String="MovieListingFragment"
    lateinit var binding:FragmentMovieListingBinding
    private val viewModel:MovieViewModel by viewModels()
    lateinit var moviesAdapter:MoviesAdapter
    val args : MovieListingFragmentArgs by navArgs()
    var categoryID=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMovieListingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryID=args.categoryID
        setUpRecyclerView()
        observer()
        binding.icBack.setOnClickListener {
            findNavController().navigate(R.id.action_movieListingFragment_to_movieCategoriesListingFragment)
        }
        binding.icAdd.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("categoryID", categoryID)
                putSerializable("movie",null)
            }
            findNavController().navigate(R.id.action_movieListingFragment_to_movieDetialsFragment,bundle)
        }
        moviesAdapter.setOnEditClickListener {
            val bundle = Bundle().apply {
                putInt("categoryID", categoryID)
                putSerializable("movie",it)
            }
            findNavController().navigate(R.id.action_movieListingFragment_to_movieDetialsFragment,bundle)
        }
        moviesAdapter.setOnDeleteClickListener { movie ->
            viewModel.deleteMovie(movie)
            Snackbar.make(view, "Movie Deleted Successfully", Snackbar.LENGTH_LONG).apply {
                setAction("Undo") {
                    viewModel.insertMovie(movie)
                }
                show()
            }
        }
    }
    private fun observer() {
        viewModel.getMoviesWithSpecificCategory(categoryID).observe(viewLifecycleOwner){
            moviesAdapter.differ.submitList(it[0].movies)
        }
    }
    private fun setUpRecyclerView() {
        val context = requireContext()
        moviesAdapter = MoviesAdapter(context)
        binding.movieRecyclerView.adapter=moviesAdapter
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.movieRecyclerView.layoutManager = layoutManager
    }
}