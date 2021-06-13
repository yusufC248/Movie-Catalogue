package com.submission.yusuf.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.submission.yusuf.data.source.local.entity.ModuleEntity
import com.submission.yusuf.data.source.local.entity.TvShowWithModule
import com.submission.yusuf.data.source.local.entity.TvsEntity
import com.submission.yusuf.data.source.local.room.TvDao

class TvLocalDatSource private constructor(private val tvDao: TvDao){
    companion object{
        private var INSTANCE: TvLocalDatSource? = null
        fun getInstance(tvDao: TvDao): TvLocalDatSource=
                INSTANCE ?: TvLocalDatSource(tvDao)
    }

    fun getAllTvShow(): DataSource.Factory<Int, TvsEntity> = tvDao.getAllTvShow()
    fun getTvShowFavorite(): DataSource.Factory<Int, TvsEntity> = tvDao.getTvShowFavorite()
    fun getTvShowWithModule(tvId: String): LiveData<TvShowWithModule> =
            tvDao.getTvShowWithModuleById(tvId)

    fun getAllModulesByCourse(tvId: String): LiveData<List<ModuleEntity>> =
            tvDao.getModulesByCourseId(tvId)

    fun insertTvShow(tvshows: List<TvsEntity>) = tvDao.insertTvShows(tvshows)
    fun insertModules(modules: List<ModuleEntity>) = tvDao.insertModules(modules)
    fun setTvShowFavorite(tvshow: TvsEntity, newState: Boolean) {
        tvshow.favorited = newState
        tvDao.updateTvShow(tvshow)
    }

    fun getModuleWithContent(moduleId: String): LiveData<ModuleEntity> =
            tvDao.getModuleById(moduleId)

    fun updateContent(content: String, moduleId: String) {
        tvDao.updateModuleByContent(content, moduleId)
    }

    fun setReadModule(module: ModuleEntity) {
        module.read = true
        tvDao.updateModule(module)
    }
}