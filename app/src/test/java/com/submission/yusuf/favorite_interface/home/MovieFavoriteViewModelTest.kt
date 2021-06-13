package com.submission.yusuf.favorite_interface.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.nhaarman.mockitokotlin2.doNothing
import com.submission.yusuf.data.source.FilmRepository
import com.submission.yusuf.data.source.local.entity.FilmEntity
import com.submission.yusuf.utils.DataCatalogue
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest{
    private lateinit var viewModel: MovieFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<PagedList<FilmEntity>>



    @Before
    fun setUp(){
        viewModel = MovieFavoriteViewModel(filmRepository)
    }

    @Test
    fun `getFavorite should be success`(){
        val expected = MutableLiveData<PagedList<FilmEntity>>()
        expected.value = PagedTestDataSources.snapshot(DataCatalogue.generateCatalogueFilm())
        `when`(filmRepository.getFavoriteListMovie()).thenReturn(expected)
        viewModel.getFavorite().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavorite().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getFavorite should be success but data is empty`(){
        val expected = MutableLiveData<PagedList<FilmEntity>>()
        expected.value = PagedTestDataSources.snapshot()
        `when`(filmRepository.getFavoriteListMovie()).thenReturn(expected)
        viewModel.getFavorite().observeForever(observer)
        verify(observer).onChanged(expected.value)
        val actualValueDataSize = viewModel.getFavorite().value?.size
        Assert.assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }

    @Test
    fun `setFavorite should add favorite to database`(){
        val dummyMovie = DataCatalogue.generateCatalogueFilm()[0]
        doNothing().`when`(filmRepository).setMovieFavorit(dummyMovie, true)
        viewModel.setFavorite(dummyMovie)
        verify(filmRepository).setMovieFavorit(dummyMovie, true)

    }

    @Test
    fun `setFavorite should remove favorite from database`(){
        val dummyMovie = DataCatalogue.generateCatalogueFilm()[0]
        dummyMovie.favorited = true
        doNothing().`when`(filmRepository).setMovieFavorit(dummyMovie, false)
        viewModel.setFavorite(dummyMovie)
        verify(filmRepository).setMovieFavorit(dummyMovie, false)

    }

    class PagedTestDataSources private constructor(private val items: List<FilmEntity>) : PositionalDataSource<FilmEntity>() {
        companion object {
            fun snapshot(items: List<FilmEntity> = listOf()): PagedList<FilmEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 11)
                        .setNotifyExecutor(Executors.newSingleThreadExecutor())
                        .setFetchExecutor(Executors.newSingleThreadExecutor())
                        .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<FilmEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<FilmEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }

}