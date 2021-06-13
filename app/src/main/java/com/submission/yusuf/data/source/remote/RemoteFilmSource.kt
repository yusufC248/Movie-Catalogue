package com.submission.yusuf.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.submission.yusuf.data.source.remote.response.ContentResponse
import com.submission.yusuf.data.source.remote.response.FilmResponse
import com.submission.yusuf.data.source.remote.response.ModuleResponse
import com.submission.yusuf.data.source.remote.response.TvsResponse
import com.submission.yusuf.utils.EspressoIdlingResource

import com.submission.yusuf.utils.JsonHelper


class RemoteFilmSource private constructor(private val JsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS: Long = 1000

        @Volatile
        private var instance: RemoteFilmSource? = null
        fun getInstance(helper: JsonHelper): RemoteFilmSource =
            instance ?: synchronized(this) {
                instance ?: RemoteFilmSource(helper).apply { instance = this }
            }
    }

    interface loadMoviesCallback {
        fun onAllMoviesReceived(filmResponse: List<FilmResponse>)
    }

    interface loadTvShowCallback {
        fun onAllTvShowReceived(filmResponse: List<TvsResponse>)
    }



    fun getAllMovie(): LiveData<ApiResponse<List<FilmResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<FilmResponse>>>()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(JsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
      }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovie
    }

    fun getAllTvShow(): LiveData<ApiResponse<List<TvsResponse>>>{
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<TvsResponse>>>()
        handler.postDelayed({
            resultTvShow.value = ApiResponse.success(JsonHelper.loadTvShow())
            EspressoIdlingResource.decrement()
     }, SERVICE_LATENCY_IN_MILLIS)
        return  resultTvShow
    }

    fun getModules(courseId: String): LiveData<ApiResponse<List<ModuleResponse>>> {
        EspressoIdlingResource.increment()
        val resultModules = MutableLiveData<ApiResponse<List<ModuleResponse>>>()
        handler.postDelayed({
            resultModules.value = ApiResponse.success(JsonHelper.loadModule(courseId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultModules
    }

    fun getContent(moduleId: String): LiveData<ApiResponse<ContentResponse>> {
        EspressoIdlingResource.increment()
        val resultContent = MutableLiveData<ApiResponse<ContentResponse>>()
        handler.postDelayed({
            resultContent.value = ApiResponse.success(JsonHelper.loadContent(moduleId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultContent
    }
}