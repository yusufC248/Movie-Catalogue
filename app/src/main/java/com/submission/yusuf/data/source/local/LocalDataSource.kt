package com.submission.yusuf.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.submission.yusuf.data.source.local.entity.FilmEntity
import com.submission.yusuf.data.source.local.entity.ModuleEntity
import com.submission.yusuf.data.source.local.entity.MovieWithModule
import com.submission.yusuf.data.source.local.room.FilmDao

class LocalDataSource private constructor(private val filmDao: FilmDao){

    companion object{
        private var INSTANCE : LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getAllMovie(): DataSource.Factory<Int, FilmEntity> = filmDao.getMovie()
    fun getAllMovieFavorit(): DataSource.Factory<Int, FilmEntity> = filmDao.getMovieFavorite()
    fun getDetailMovie(movId: String): LiveData<MovieWithModule> = filmDao.getMovieDetail(movId)
    fun insertMovie(movie: List<FilmEntity>){
        filmDao.insertMovies(movie)
    }


    fun insertModules(modules: List<ModuleEntity>) = filmDao.insertModules(modules)


    fun setMovieFav(movie: FilmEntity, newState:Boolean){
       movie.favorited = newState
        filmDao.movieUpdate(movie)
    }

    fun getModuleWithContent(moduleId: String): LiveData<ModuleEntity> =
            filmDao.getModuleById(moduleId)

    fun updateContent(content: String, moduleId: String) {
        filmDao.updateModuleByContent(content, moduleId)
    }

    fun setReadModule(module: ModuleEntity) {
        module.read = true
        filmDao.updateModule(module)
    }
    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>> =
            filmDao.getModulesByCourseId(courseId)




}