package com.moyehics.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moyehics.movieapp.data.room.entities.Movie
import com.moyehics.movieapp.databinding.MovieCardItemBinding

class MoviesAdapter(val context: Context):
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private val differCallBack = object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.movieID == newItem.movieID
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = MovieCardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MoviesViewHolder(itemView)
    }
    inner class MoviesViewHolder (val binding:MovieCardItemBinding ) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentItem=differ.currentList[position]
        holder.binding.txtMovieName.text=currentItem.name
        holder.binding.txtDesc.text=currentItem.description
        holder.binding.icEdite.setOnClickListener {
            onEditClickedListener?.let { it(differ.currentList[position]) }
        }
        holder.binding.icDelete.setOnClickListener {
            onDeleteClickedListener?.let { it(differ.currentList[position]) }
        }

    }

    override fun getItemCount(): Int =differ.currentList.size

    private var onEditClickedListener:((Movie) -> Unit)?=null

    fun setOnEditClickListener(listener:(Movie) -> Unit){
        onEditClickedListener=listener
    }
    private var onDeleteClickedListener:((Movie) -> Unit)?=null

    fun setOnDeleteClickListener(listener:(Movie) -> Unit){
        onDeleteClickedListener=listener
    }
}