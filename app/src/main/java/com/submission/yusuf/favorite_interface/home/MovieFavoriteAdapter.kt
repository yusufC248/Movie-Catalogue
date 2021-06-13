package com.submission.yusuf.favorite_interface.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.yusuf.R
import com.submission.yusuf.data.source.local.entity.FilmEntity
import com.submission.yusuf.databinding.FragmentModuleListBinding
import com.submission.yusuf.detail.DetailFilmActivity

class MovieFavoriteAdapter: PagedListAdapter<FilmEntity, MovieFavoriteAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FilmEntity>(){
            override fun areItemsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
                return oldItem.filmId == newItem.filmId
            }

            override fun areContentsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieFavoriteAdapter.MovieViewHolder {
        val activityFragmentModuleListBinding =
            FragmentModuleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MovieAdapter.MovieViewHolder(activityFragmentModuleListBinding)
        return MovieViewHolder(activityFragmentModuleListBinding)
    }

    override fun onBindViewHolder(holder: MovieFavoriteAdapter.MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null){
            holder.bind(movie)
        }
    }

    fun getSwipeData(swipe: Int): FilmEntity? = getItem(swipe)

    inner class MovieViewHolder(private val binding: FragmentModuleListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: FilmEntity){
            with(binding){
                tvDataTitle.text = movie.title
                tvDataDesc.text = movie.description
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.KEY_FILM, movie.filmId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(movie.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgFront)

            }
            }
        }
    }
