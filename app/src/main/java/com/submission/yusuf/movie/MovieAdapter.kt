package com.submission.yusuf.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.yusuf.data.source.local.entity.FilmEntity
import com.submission.yusuf.R
import com.submission.yusuf.databinding.FragmentModuleListBinding
import com.submission.yusuf.detail.DetailFilmActivity
import androidx.recyclerview.widget.DiffUtil

class MovieAdapter: PagedListAdapter<FilmEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {



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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val activityFragmentModuleListBinding =
            FragmentModuleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(activityFragmentModuleListBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if(movie != null){
            holder.bind(movie)
        }
    }


    class MovieViewHolder(private val binding: FragmentModuleListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mov: FilmEntity) {
            with(binding) {
                tvDataTitle.text = mov.title
                tvDataDesc.text = mov.description
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.KEY_FILM, mov.filmId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(mov.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgFront)

            }

        }

    }
}