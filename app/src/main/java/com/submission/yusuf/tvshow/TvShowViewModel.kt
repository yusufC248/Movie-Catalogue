package com.submission.yusuf.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.submission.yusuf.data.source.TvRepository
import com.submission.yusuf.data.source.local.entity.TvsEntity
import com.submission.yusuf.vo.Resource

class TvShowViewModel(private val tvRepository: TvRepository) : ViewModel() {
    fun returnTvShow(): LiveData<Resource<PagedList<TvsEntity>>> = tvRepository.getAllTvShow()
}