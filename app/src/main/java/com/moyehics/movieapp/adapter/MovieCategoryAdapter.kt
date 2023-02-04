package com.moyehics.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moyehics.movieapp.data.room.entities.MovieCategory
import com.moyehics.movieapp.databinding.MovieCategoryCardItemBinding

class MovieCategoryAdapter(val context: Context):
    RecyclerView.Adapter<MovieCategoryAdapter.MovieCategoryViewHolder>() {
    private val differCallBack = object : DiffUtil.ItemCallback<MovieCategory>(){
        override fun areItemsTheSame(oldItem: MovieCategory, newItem: MovieCategory): Boolean {
            return oldItem.movieCategoryName == newItem.movieCategoryName
        }

        override fun areContentsTheSame(oldItem: MovieCategory, newItem: MovieCategory): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCategoryViewHolder {
        val itemView = MovieCategoryCardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieCategoryViewHolder(itemView)
    }
    inner class MovieCategoryViewHolder (val binding:MovieCategoryCardItemBinding ) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MovieCategoryViewHolder, position: Int) {
        holder.binding.txtMovieCategoryName.text = differ.currentList[position].movieCategoryName
        holder.itemView.setOnClickListener {
            onItemClickedListener?.let { it(differ.currentList[position]) }
        }
    }

    override fun getItemCount(): Int =differ.currentList.size

    private var onItemClickedListener:((MovieCategory) -> Unit)?=null

    fun setOnItemClickListener(listener:(MovieCategory) -> Unit){
        onItemClickedListener=listener
    }
}