package com.submission.yusuf.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.submission.yusuf.data.source.FilmRepository
import com.submission.yusuf.detail.DetailMovieViewModel
import com.submission.yusuf.di.Injection
import com.submission.yusuf.favorite_interface.home.MovieFavoriteViewModel
import com.submission.yusuf.movie.MovieViewModel

class ViewModelfactory private constructor(private val filmRepository: FilmRepository): ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var instance: ViewModelfactory? = null

        fun getInstance(context: Context): ViewModelfactory =
            instance ?: synchronized(this){
                instance ?: ViewModelfactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       when{
           modelClass.isAssignableFrom(MovieViewModel::class.java)->{
               return MovieViewModel(filmRepository) as T
           }
           modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
               return DetailMovieViewModel(filmRepository) as T
           }
           modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java) ->{
               return  MovieFavoriteViewModel(filmRepository) as T
           }
           else -> throw  Throwable("Unknown ViewModel class: "+ modelClass.name)
       }
    }
}