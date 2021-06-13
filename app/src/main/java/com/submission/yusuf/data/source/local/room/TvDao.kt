package com.submission.yusuf.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.submission.yusuf.data.source.local.entity.ModuleEntity
import com.submission.yusuf.data.source.local.entity.TvShowWithModule
import com.submission.yusuf.data.source.local.entity.TvsEntity

@Dao
interface TvDao {
    @Query("SELECT * FROM tvshowentities")
    fun getAllTvShow(): DataSource.Factory<Int, TvsEntity>

    @Query("SELECT * FROM tvshowentities where favorited = 1")
    fun getTvShowFavorite(): DataSource.Factory<Int, TvsEntity>

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE filmId = :courseId")
    fun getTvShowWithModuleById(courseId: String): LiveData<TvShowWithModule>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(courses: List<TvsEntity>)

    @Update
    fun updateTvShow(course: TvsEntity)

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