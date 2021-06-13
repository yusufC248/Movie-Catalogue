package com.submission.yusuf.favorite_interface.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.submission.yusuf.data.source.FilmRepository
import com.submission.yusuf.data.source.local.entity.FilmEntity

class MovieFavoriteViewModel(private val filmRepository: FilmRepository): ViewModel() {
    fun getFavorite(): LiveData<PagedList<FilmEntity>> = filmRepository.getFavoriteListMovie()
    fun setFavorite(filmEntity: FilmEntity){
        val newState = !filmEntity.favorited
        filmRepository.setMovieFavorit(filmEntity, newState)
    }
}