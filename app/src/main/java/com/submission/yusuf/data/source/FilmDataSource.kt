package com.submission.yusuf.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.submission.yusuf.data.source.local.entity.FilmEntity
import com.submission.yusuf.data.source.local.entity.ModuleEntity
import com.submission.yusuf.data.source.local.entity.MovieWithModule
import com.submission.yusuf.vo.Resource

interface FilmDataSource {
    fun getAllMovie(): LiveData<Resource<PagedList<FilmEntity>>>
    fun getMovieWithModules(filmId: String) : LiveData<Resource<MovieWithModule>>
    fun getAllModules(filmId: String): LiveData<Resource<List<ModuleEntity>>>
    fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>>
    fun getFavoriteListMovie(): LiveData<PagedList<FilmEntity>>
    fun setMovieFavorit(movie: FilmEntity, state: Boolean)
    fun setReadModule(module: ModuleEntity)
}