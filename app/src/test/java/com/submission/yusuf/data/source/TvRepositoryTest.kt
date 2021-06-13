package com.submission.yusuf.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.submission.yusuf.PagedListUtil
import com.submission.yusuf.data.source.local.TvLocalDatSource
import com.submission.yusuf.data.source.local.entity.TvShowWithModule
import com.submission.yusuf.data.source.local.entity.TvsEntity
import com.submission.yusuf.data.source.remote.RemoteFilmSource
import com.submission.yusuf.utils.AppExecutors
import com.submission.yusuf.utils.DataCatalogue
import com.submission.yusuf.utils.LiveDatatestUtil
import com.submission.yusuf.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class TvRepositoryTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteFilmSource = Mockito.mock(RemoteFilmSource::class.java)
    private val localDataSource = Mockito.mock(TvLocalDatSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val tvRepository = FakeTvRepository(remoteFilmSource, localDataSource, appExecutors)
    private val tvResponse = DataCatalogue.generateDummyCatalogueTVShows()
    private val tvId = tvResponse[0].filmId
    private val moduleResponse = DataCatalogue.generateDummyModules(tvId)
    private val moduleId = moduleResponse[0].filmId


    @Test
    fun getAllTvShow(){
        val dataSource = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvsEntity>
        `when`(localDataSource.getTvShowFavorite()).thenReturn(dataSource)
        tvRepository.getFavoriteListTvShow()
        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataCatalogue.generateCatalogueFilm()))
        verify(localDataSource).getTvShowFavorite()
        assertNotNull(tvEntities)
        assertEquals(tvResponse.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShowWithModule(){
        val dummyEtity = MutableLiveData<TvShowWithModule>()
       dummyEtity.value = DataCatalogue.generateDummyTvWithModules(DataCatalogue.generateCatalogueTVShows()[0], false)
        `when`<LiveData<TvShowWithModule>>(localDataSource.getTvShowWithModule(tvId)).thenReturn(dummyEtity)
        val tvEntities = LiveDatatestUtil.getValue(tvRepository.getTvShowWithModules(tvId))
        verify(localDataSource).getTvShowWithModule(tvId)
        assertNotNull(tvEntities.data)
        assertNotNull(tvEntities.data?.mCourse?.title)
        assertEquals(tvResponse[0].title, tvEntities.data?.mCourse?.title)
    }

    @Test
    fun getFavoriteTvShow(){
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvsEntity>
        `when`(localDataSource.getTvShowFavorite()).thenReturn(dataSourceFactory)
        tvRepository.getFavoriteListTvShow()
        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataCatalogue.generateCatalogueTVShows()))
        verify(localDataSource).getTvShowFavorite()
        assertNotNull(tvEntities)
        assertEquals(tvResponse.size.toLong(), tvEntities.data?.size?.toLong())

    }
}