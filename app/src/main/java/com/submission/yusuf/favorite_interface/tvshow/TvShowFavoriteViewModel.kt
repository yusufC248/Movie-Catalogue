package com.submission.yusuf.favorite_interface.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.submission.yusuf.data.source.TvRepository

import com.submission.yusuf.data.source.local.entity.TvsEntity

class TvShowFavoriteViewModel(private val tvRepository: TvRepository): ViewModel() {
    fun getFavorite(): LiveData<PagedList<TvsEntity>> = tvRepository.getFavoriteListTvShow()
    fun setFavorite(tvsEntity: TvsEntity){
        val newState = !tvsEntity.favorited
        tvRepository.setTvShowFavorit(tvsEntity, newState)
    }
}