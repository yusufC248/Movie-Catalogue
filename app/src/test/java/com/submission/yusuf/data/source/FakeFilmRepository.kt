package com.submission.yusuf.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.submission.yusuf.data.source.local.LocalDataSource
import com.submission.yusuf.data.source.local.entity.FilmEntity
import com.submission.yusuf.data.source.local.entity.ModuleEntity
import com.submission.yusuf.data.source.local.entity.MovieWithModule
import com.submission.yusuf.data.source.remote.ApiResponse
import com.submission.yusuf.data.source.remote.RemoteFilmSource
import com.submission.yusuf.data.source.remote.response.ContentResponse
import com.submission.yusuf.data.source.remote.response.FilmResponse
import com.submission.yusuf.data.source.remote.response.ModuleResponse
import com.submission.yusuf.utils.AppExecutors
import com.submission.yusuf.vo.Resource

class FakeFilmRepository constructor(
private val remoteFilmSource: RemoteFilmSource,
private val localDataSource: LocalDataSource,
private val appExecutors: AppExecutors
)
: FilmDataSource {


    override fun getAllMovie(): LiveData<Resource<PagedList<FilmEntity>>> {
        return object : NetworkBoundResources<PagedList<FilmEntity>, List<FilmResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<FilmEntity>> {
                val config  = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<FilmEntity>?): Boolean = data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<FilmResponse>>> = remoteFilmSource.getAllMovie()
            public override fun saveCallResult(data: List<FilmResponse>) {
                val movieList = ArrayList<FilmEntity>()
                for (response in data){
                    val movie = FilmEntity(
                        response.filmId,
                        response.title,
                        response.description,
                        response.imagePath,
                        response.background,
                        response.rating)
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getMovieWithModules(filmId: String): LiveData<Resource<MovieWithModule>> {
        return object : NetworkBoundResources<MovieWithModule, List<ModuleResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<MovieWithModule> =
                localDataSource.getDetailMovie(filmId)


            override fun shouldFetch(data: MovieWithModule?): Boolean =
                data?.mModules == null || data.mModules.isEmpty()

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

                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }

    override fun getAllModules(filmId: String): LiveData<Resource<List<ModuleEntity>>> {
        return object : NetworkBoundResources<List<ModuleEntity>, List<ModuleResponse>>(appExecutors){
            public override fun loadFromDB(): LiveData<List<ModuleEntity>> = localDataSource.getAllModulesByCourse(filmId)

            override fun shouldFetch(data: List<ModuleEntity>?): Boolean = data == null || data.isEmpty()

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

                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }

    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object : NetworkBoundResources<ModuleEntity, ContentResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<ModuleEntity> =
                localDataSource.getModuleWithContent(moduleId)

            override fun shouldFetch(data: ModuleEntity?): Boolean =
                data?.contentEntity == null

            override fun createCall(): LiveData<ApiResponse<ContentResponse>> =
                remoteFilmSource.getContent(moduleId)

            override fun saveCallResult(data: ContentResponse) =
                localDataSource.updateContent(data.content, moduleId)
        }.asLiveData()
    }


    override fun getFavoriteListMovie(): LiveData<PagedList<FilmEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return  LivePagedListBuilder(localDataSource.getAllMovieFavorit(), config).build()
    }

    override fun setMovieFavorit(movie: FilmEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieFav(movie, state) }


    override fun setReadModule(module: ModuleEntity) =
        appExecutors.diskIO().execute { localDataSource.setReadModule(module) }

}