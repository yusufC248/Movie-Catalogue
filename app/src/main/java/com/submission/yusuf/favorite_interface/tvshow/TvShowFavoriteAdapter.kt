package com.submission.yusuf.favorite_interface.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.yusuf.R
import com.submission.yusuf.data.source.local.entity.TvsEntity
import com.submission.yusuf.databinding.FragmentModuleListBinding
import com.submission.yusuf.detail.DetailFilmActivity
import com.submission.yusuf.detail.DetailTvShowActivity

class TvShowFavoriteAdapter: PagedListAdapter<TvsEntity, TvShowFavoriteAdapter.TvShowViewHolder>(DIFF_CALLBACK) {
    companion object{
        private val DIFF_CALLBACK = object  : DiffUtil.ItemCallback<TvsEntity>(){
            override fun areItemsTheSame(oldItem: TvsEntity, newItem: TvsEntity): Boolean {
                return oldItem.filmId == newItem.filmId
            }

            override fun areContentsTheSame(oldItem: TvsEntity, newItem: TvsEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowFavoriteAdapter.TvShowViewHolder {
        val activityFragmentModuleListBinding =
            FragmentModuleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(activityFragmentModuleListBinding)
    }

    override fun onBindViewHolder(holder: TvShowFavoriteAdapter.TvShowViewHolder, position: Int) {
        val tvshow = getItem(position)
        if (tvshow != null){
            holder.bind(tvshow)
        }
    }

    fun getSwipeData(swiipe: Int): TvsEntity? = getItem(swiipe)

    inner class TvShowViewHolder(private val binding: FragmentModuleListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(tvshow: TvsEntity){
            with(binding){
                tvDataTitle.text = tvshow.title
                tvDataDesc.text = tvshow.description
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailFilmActivity.KEY_FILM, tvshow.filmId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tvshow.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgFront)

            }
        }
    }
}