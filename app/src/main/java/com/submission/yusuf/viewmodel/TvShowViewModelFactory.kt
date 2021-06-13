package com.submission.yusuf.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.submission.yusuf.data.source.TvRepository
import com.submission.yusuf.detail.DetailTvShowViewModel
import com.submission.yusuf.di.Injection
import com.submission.yusuf.favorite_interface.tvshow.TvShowFavoriteViewModel
import com.submission.yusuf.tvshow.TvShowViewModel

class TvShowViewModelFactory private constructor(private val tvRepository: TvRepository): ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var instance: TvShowViewModelFactory? = null

        fun getInstance(context: Context): TvShowViewModelFactory =
            instance ?: synchronized(this){
                instance ?: TvShowViewModelFactory(Injection.tvshowProfideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(TvShowViewModel::class.java) ->{
                return TvShowViewModel(tvRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) ->{
                return DetailTvShowViewModel(tvRepository) as T
            }
            modelClass.isAssignableFrom(TvShowFavoriteViewModel::class.java) -> {
                return TvShowFavoriteViewModel(tvRepository) as T
            }
            else ->  throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}