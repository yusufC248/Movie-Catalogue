package com.submission.yusuf.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.submission.yusuf.data.source.TvRepository
import com.submission.yusuf.data.source.local.entity.TvShowWithModule
import com.submission.yusuf.vo.Resource

class DetailTvShowViewModel(private val tvRepository: TvRepository): ViewModel() {
    private val tvshowId = MutableLiveData<String>()

    fun setSelectedCourse(tvshowId: String) {
        this.tvshowId.value = tvshowId
    }

    var tvshowModule: LiveData<Resource<TvShowWithModule>> =
        Transformations.switchMap(tvshowId) { mTvSHowId ->
//        filmRepository.getMovieWithModules(mCourseId)
            tvRepository.getTvShowWithModules(mTvSHowId)
        }

    fun setBookmark() {
        val moduleResource = tvshowModule.value
        if (moduleResource != null) {
            val tvshowWithModule = moduleResource.data
            if (tvshowWithModule != null) {
                val tvshowEntity = tvshowWithModule.mCourse
                val newState = !tvshowEntity.favorited
//                filmRepository.setMovieFavorit(courseEntity, newState)
                tvRepository.setTvShowFavorit(tvshowEntity, newState)
            }

        }
    }
}