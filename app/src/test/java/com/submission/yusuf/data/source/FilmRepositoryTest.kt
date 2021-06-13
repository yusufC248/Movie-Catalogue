package com.submission.yusuf.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.submission.yusuf.PagedListUtil
import com.submission.yusuf.data.source.local.LocalDataSource
import com.submission.yusuf.data.source.local.entity.FilmEntity
import com.submission.yusuf.data.source.local.entity.MovieWithModule
import com.submission.yusuf.data.source.remote.RemoteFilmSource
import com.submission.yusuf.utils.AppExecutors
import com.submission.yusuf.utils.DataCatalogue
import com.submission.yusuf.utils.LiveDatatestUtil
import com.submission.yusuf.vo.Resource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class FilmRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteFilmSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val filmRepository = FakeFilmRepository(remote, local, appExecutors)
    private val mvResponse = DataCatalogue.generateDummyCatalogueFilm()
    private val movieId = mvResponse[0].filmId
    private val moduleResponse  = DataCatalogue.generateDummyModules(movieId)
    private val moduleId = moduleResponse[0].moduleId


    @Test
    fun testGetAllMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        `when`(local.getAllMovie()).thenReturn(dataSourceFactory)
        filmRepository.getAllMovie()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataCatalogue.generateCatalogueFilm()))
        verify(local).getAllMovie()
        assertNotNull(movieEntity.data)
        assertEquals(mvResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getFavoriteMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        `when`(local.getAllMovieFavorit()).thenReturn(dataSourceFactory)
        filmRepository.getFavoriteListMovie()
        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataCatalogue.generateCatalogueFilm()))
        verify(local).getAllMovieFavorit()
        assertNotNull(movieEntity)
        assertEquals(mvResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }


    @Test
    fun geMovieWithModules(){
        val dummyEntity = MutableLiveData<MovieWithModule>()
        dummyEntity.value = DataCatalogue.generateDummyMovieWithModules(DataCatalogue.generateCatalogueFilm()[0], false)
        `when`<LiveData<MovieWithModule>>(local.getDetailMovie(movieId)).thenReturn(dummyEntity)

       val movieEntities = LiveDatatestUtil.getValue(filmRepository.getMovieWithModules(movieId))
        verify(local).getDetailMovie(movieId)
        assertNotNull(movieEntities.data)
        assertNotNull(movieEntities.data?.mMovie?.title)
        assertEquals(mvResponse[0].title, movieEntities.data?.mMovie?.title)
    }



}