package com.submission.yusuf.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.submission.yusuf.data.source.FilmRepository
import com.submission.yusuf.data.source.local.entity.MovieWithModule
import com.submission.yusuf.vo.Resource

class DetailMovieViewModel (private val filmRepository: FilmRepository) : ViewModel() {
    val courseId = MutableLiveData<String>()

    fun setSelectedCourse(courseId: String) {
        this.courseId.value = courseId
    }

    var movieModule: LiveData<Resource<MovieWithModule>> = Transformations.switchMap(courseId) { mCourseId ->
       filmRepository.getMovieWithModules(mCourseId)
    }

    fun setBookmark() {
        val moduleResource = movieModule.value
        if (moduleResource != null) {
            val courseWithModule = moduleResource.data
            if (courseWithModule != null) {
                val courseEntity = courseWithModule.mMovie
                val newState = !courseEntity.favorited
                filmRepository.setMovieFavorit(courseEntity, newState)
            }
        }
    }
}