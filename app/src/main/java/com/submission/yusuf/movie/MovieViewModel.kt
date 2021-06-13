package com.submission.yusuf.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.submission.yusuf.data.source.FilmRepository
import com.submission.yusuf.data.source.local.entity.FilmEntity
import com.submission.yusuf.vo.Resource

class MovieViewModel (private val filmRepository: FilmRepository) : ViewModel() {

    fun returnMovie(): LiveData<Resource<PagedList<FilmEntity>>> = filmRepository.getAllMovie()
}