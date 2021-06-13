package com.submission.yusuf.tvshow

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
import com.submission.yusuf.detail.DetailTvShowActivity

class TvShowAdapter: PagedListAdapter<TvsEntity, TvShowAdapter.TvShowHolder>(DIFF_CALLBACK) {


    companion object{
        private val DIFF_CALLBACK = object :  DiffUtil.ItemCallback<TvsEntity>(){
            override fun areItemsTheSame(oldItem: TvsEntity, newItem: TvsEntity): Boolean {
                return  oldItem.filmId == newItem.filmId
            }

            override fun areContentsTheSame(oldItem: TvsEntity, newItem: TvsEntity): Boolean {
                return  oldItem == newItem
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowHolder{
        val activityFragmentModuleListBinding = FragmentModuleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowHolder(activityFragmentModuleListBinding)
    }

    override fun onBindViewHolder(holder: TvShowHolder, position: Int) {
        val tv = getItem(position)
       if(tv != null){
           holder.bind(tv)
       }
    }



    class TvShowHolder(private val binding: FragmentModuleListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvsEntity){
            with(binding){
                tvDataTitle.text = tv.title
                tvDataDesc.text = tv.description
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.KEY_FILM, tv.filmId)
//                    intent.putExtra(DetailFilmActivity.KEY_TYPE, "TVSHOW")
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(tv.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgFront)
            }
        }
    }


}