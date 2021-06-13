package com.submission.yusuf.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.submission.yusuf.data.source.local.TvLocalDatSource
import com.submission.yusuf.data.source.local.entity.ModuleEntity
import com.submission.yusuf.data.source.local.entity.TvShowWithModule
import com.submission.yusuf.data.source.local.entity.TvsEntity
import com.submission.yusuf.data.source.remote.ApiResponse
import com.submission.yusuf.data.source.remote.RemoteFilmSource
import com.submission.yusuf.data.source.remote.response.ContentResponse
import com.submission.yusuf.data.source.remote.response.ModuleResponse
import com.submission.yusuf.data.source.remote.response.TvsResponse
import com.submission.yusuf.utils.AppExecutors
import com.submission.yusuf.vo.Resource

class FakeTvRepository constructor(
    private val remoteFilmSource: RemoteFilmSource,
    private val tvlocalDataSource: TvLocalDatSource,
    private val appExecutors: AppExecutors
): TvDataSource{

    companion object {
        @Volatile
        private var instance: TvRepository? = null

    }

    override fun getAllTvShow(): LiveData<Resource<PagedList<TvsEntity>>> {
        return object : NetworkBoundResources<PagedList<TvsEntity>, List<TvsResponse>>(appExecutors){
            public override fun loadFromDB(): LiveData<PagedList<TvsEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(tvlocalDataSource.getAllTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvsEntity>?): Boolean = data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvsResponse>>> = remoteFilmSource.getAllTvShow()

            public override fun saveCallResult(data: List<TvsResponse>) {
                val tvShowList = ArrayList<TvsEntity>()
                for(response in data){
                    val tvshow = TvsEntity(response.filmId,
                        response.title,
                        response.description,
                        response.imagePath,
                        response.background,
                        response.rating)
                    tvShowList.add(tvshow)
                }
                tvlocalDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getTvShowWithModules(filmId: String): LiveData<Resource<TvShowWithModule>> {
        return object : NetworkBoundResources<TvShowWithModule, List<ModuleResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<TvShowWithModule> = tvlocalDataSource.getTvShowWithModule(filmId)
            override fun shouldFetch(data: TvShowWithModule?): Boolean = data == null || data.mModules.isEmpty()
            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> = remoteFilmSource.getModules(filmId)
            override fun saveCallResult(data: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in data) {
                    val course = ModuleEntity(response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false)

                    moduleList.add(course)
                }
                tvlocalDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }


    override fun getAllModules(filmId: String): LiveData<Resource<List<ModuleEntity>>> {
        return object : NetworkBoundResources<List<ModuleEntity>, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<ModuleEntity>> =
                tvlocalDataSource.getAllModulesByCourse(filmId)

            override fun shouldFetch(data: List<ModuleEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> =
                remoteFilmSource.getModules(filmId)

            override fun saveCallResult(data: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in data) {
                    val course = ModuleEntity(response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false)

                    moduleList.add(course)
                }

                tvlocalDataSource.insertModules(moduleList)

            }
        }.asLiveData()
    }

    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object : NetworkBoundResources<ModuleEntity, ContentResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<ModuleEntity> =
                tvlocalDataSource.getModuleWithContent(moduleId)

            override fun shouldFetch(data: ModuleEntity?): Boolean =
                data?.contentEntity == null

            override fun createCall(): LiveData<ApiResponse<ContentResponse>> =
                remoteFilmSource.getContent(moduleId)

            override fun saveCallResult(data: ContentResponse) =
                tvlocalDataSource.updateContent(data.content, moduleId)
        }.asLiveData()
    }

    override fun getFavoriteListTvShow(): LiveData<PagedList<TvsEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(tvlocalDataSource.getTvShowFavorite(), config).build()
    }

    override fun setTvShowFavorit(tvshow: TvsEntity, state: Boolean) = appExecutors.diskIO().execute { tvlocalDataSource.setTvShowFavorite(tvshow, state) }

    override fun setReadModule(module: ModuleEntity) = appExecutors.diskIO().execute { tvlocalDataSource.setReadModule(module) }
}