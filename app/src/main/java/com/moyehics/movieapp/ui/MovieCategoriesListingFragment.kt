package com.moyehics.movieapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.moyehics.movieapp.R
import com.moyehics.movieapp.adapter.MovieCategoryAdapter
import com.moyehics.movieapp.data.room.entities.MovieCategory
import com.moyehics.movieapp.databinding.BottomSheetDialogBinding
import com.moyehics.movieapp.databinding.FragmentMovieCategoriesListingBinding
import com.moyehics.movieapp.util.snackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieCategoriesListingFragment : Fragment() {
    val TAG:String="MovieCategoriesListingFragment"
    lateinit var binding: FragmentMovieCategoriesListingBinding
    private val viewModel:MovieViewModel by viewModels()
    lateinit var movieCategoryAdapter: MovieCategoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentMovieCategoriesListingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        observer()
        movieCategoryAdapter.setOnItemClicListener {
            val bundle = Bundle().apply {
                putInt("categoryID", it.movieCategoryID)
            }
            findNavController().navigate(R.id.action_movieCategoriesListingFragment_to_movieListingFragment,bundle)
        }
        binding.icAdd.setOnClickListener {
            val bottomSheetDailog = BottomSheetDialog(requireContext())
            val inflater=LayoutInflater.from(requireContext())
            val bindingBottomSheetDialog=BottomSheetDialogBinding.inflate(inflater)
            bottomSheetDailog.setContentView(bindingBottomSheetDialog.root)
            bindingBottomSheetDialog.btnSave.setOnClickListener {
                if(!bindingBottomSheetDialog.categoryNameEditeText.text.toString().isNullOrBlank()){
                    viewModel.insertMovieCategory(MovieCategory(movieCategoryName = bindingBottomSheetDialog.categoryNameEditeText.text.toString()))
                    observer()
                    bottomSheetDailog.dismiss()
                    snackBar("New Category Added Successfully")
                }else{
                    bindingBottomSheetDialog.categoryNameEditeText.error="Please Enter Category Name"
                }
            }
            bottomSheetDailog.show()

        }

    }

    private fun observer() {
        viewModel.getMovieCategories().observe(viewLifecycleOwner){
            movieCategoryAdapter.differ.submitList(it)
        }
    }

    private fun setUpRecyclerView() {
        val context = requireContext()
        movieCategoryAdapter = MovieCategoryAdapter(context)
        binding.categoriesRecyclerView.adapter=movieCategoryAdapter
        val layoutManager = GridLayoutManager(context,2)
        binding.categoriesRecyclerView.layoutManager = layoutManager
    }
}