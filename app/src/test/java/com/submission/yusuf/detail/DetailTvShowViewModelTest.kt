package com.submission.yusuf.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.submission.yusuf.data.source.TvRepository
import com.submission.yusuf.data.source.local.entity.TvShowWithModule
import com.submission.yusuf.utils.DataCatalogue
import com.submission.yusuf.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest{
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = DataCatalogue.generateCatalogueTVShows()[0]
    private val tvId = dummyTvShow.filmId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvRepository: TvRepository

    @Mock
    private lateinit var observer: Observer<Resource<TvShowWithModule>>

    @Before
    fun setUp(){
        viewModel = DetailTvShowViewModel(tvRepository)
        viewModel.setSelectedCourse(tvId)
    }

    @Test
    fun `getTvWithModule should be success`(){
        val expected = MutableLiveData<Resource<TvShowWithModule>>()
        expected.value = Resource.success(DataCatalogue.generateDummyTvWithModules(dummyTvShow, true))
        `when`(tvRepository.getTvShowWithModules(tvId)).thenReturn(expected)
        viewModel.tvshowModule.observeForever(observer)
        verify(observer).onChanged(expected.value)
        val expectedvalue = expected.value
        val actualvalue = viewModel.tvshowModule.value
        assertEquals(expectedvalue, actualvalue)
    }

    @Test
    fun `setFavorite should not add favorite when tvshowModule is Empty`(){
        viewModel.setBookmark()
        verify(tvRepository, times(0)).setTvShowFavorit(dummyTvShow, true)

    }

    @Test
    fun `setFavorite should add favorite when tvshowModule is not empty and not favorited`(){
        val expected = MutableLiveData<Resource<TvShowWithModule>>()
        expected.value = Resource.success(DataCatalogue.generateDummyTvWithModules(dummyTvShow, false))

        `when`(tvRepository.getTvShowWithModules(tvId)).thenReturn(expected)
        viewModel.tvshowModule.observeForever(observer)

        doNothing().`when`(tvRepository).setTvShowFavorit(dummyTvShow, true)

        viewModel.setBookmark()
        verify(tvRepository, times(1)).setTvShowFavorit(dummyTvShow, true)

    }

    @Test
    fun `setFavorite should remove from favorite when tvshowModule is not Empty and already favorited`(){
        val expected = MutableLiveData<Resource<TvShowWithModule>>()
        expected.value = Resource.success(DataCatalogue.generateDummyTvWithModules(dummyTvShow, true))

        `when`(tvRepository.getTvShowWithModules(tvId)).thenReturn(expected)
        viewModel.tvshowModule.observeForever(observer)

        doNothing().`when`(tvRepository).setTvShowFavorit(dummyTvShow, false)

        viewModel.setBookmark()
        verify(tvRepository, times(1)).setTvShowFavorit(dummyTvShow, false)

    }
}