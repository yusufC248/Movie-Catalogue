package com.submission.yusuf.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.submission.yusuf.data.source.local.entity.ModuleEntity
import com.submission.yusuf.data.source.local.entity.TvShowWithModule
import com.submission.yusuf.data.source.local.entity.TvsEntity
import com.submission.yusuf.vo.Resource

interface TvDataSource {
    fun getAllTvShow(): LiveData<Resource<PagedList<TvsEntity>>>
    fun getTvShowWithModules(filmId: String) : LiveData<Resource<TvShowWithModule>>
    fun getAllModules(filmId: String): LiveData<Resource<List<ModuleEntity>>>
    fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>>
    fun getFavoriteListTvShow(): LiveData<PagedList<TvsEntity>>
    fun setTvShowFavorit(tvshow: TvsEntity, state: Boolean)
    fun setReadModule(module: ModuleEntity)
}