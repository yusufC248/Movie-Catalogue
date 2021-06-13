package com.submission.yusuf.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.submission.yusuf.data.source.local.entity.FilmEntity
import com.submission.yusuf.data.source.local.entity.ModuleEntity
import com.submission.yusuf.data.source.local.entity.MovieWithModule

@Dao
interface FilmDao {
    @Query("SELECT * FROM movieentities")
    fun getMovie(): DataSource.Factory<Int, FilmEntity>


    @Query("SELECT * FROM movieentities WHERE favorited = 1")
    fun getMovieFavorite(): DataSource.Factory<Int, FilmEntity>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE filmId = :filmId")
    fun getMovieDetail(filmId: String): LiveData<MovieWithModule>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<FilmEntity>)


    @Update
    fun movieUpdate(movie: FilmEntity)


    @Query("SELECT * FROM moduleentities WHERE filmId = :courseId")
    fun getModulesByCourseId(courseId: String): LiveData<List<ModuleEntity>>

    @Query("SELECT * FROM moduleentities WHERE moduleId = :moduleId")
    fun getModuleById(moduleId: String): LiveData<ModuleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModules(module: List<ModuleEntity>)

    @Update
    fun updateModule(module: ModuleEntity)

    @Query("UPDATE moduleentities SET content = :content WHERE moduleId = :moduleId")
    fun updateModuleByContent(content: String, moduleId: String)

}