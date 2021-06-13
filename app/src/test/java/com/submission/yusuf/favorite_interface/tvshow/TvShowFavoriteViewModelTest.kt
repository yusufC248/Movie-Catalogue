package com.submission.yusuf.favorite_interface.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.nhaarman.mockitokotlin2.doNothing
import com.submission.yusuf.data.source.TvRepository
import com.submission.yusuf.data.source.local.entity.TvsEntity
import com.submission.yusuf.utils.DataCatalogue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class TvShowFavoriteViewModelTest {
    private lateinit var viewModel: TvShowFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvRepository: TvRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvsEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowFavoriteViewModel(tvRepository)
    }

    @Test
    fun `getFavorite should be success`() {
        val expected = MutableLiveData<PagedList<TvsEntity>>()
        expected.value = PagedTestDataSources.snapshot(DataCatalogue.generateCatalogueTVShows())
        Mockito.`when`(tvRepository.getFavoriteListTvShow()).thenReturn(expected)
        viewModel.getFavorite().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavorite().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        Assert.assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getFavorite should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<TvsEntity>>()
        expected.value = PagedTestDataSources.snapshot()
        Mockito.`when`(tvRepository.getFavoriteListTvShow()).thenReturn(expected)
        viewModel.getFavorite().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)
        val actualValueDataSize = viewModel.getFavorite().value?.size
        Assert.assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }

    @Test
    fun `setFavorite should add favorite to database`() {
        val dummyTvShow = DataCatalogue.generateCatalogueTVShows()[0]
        doNothing().`when`(tvRepository).setTvShowFavorit(dummyTvShow, true)
        viewModel.setFavorite(dummyTvShow)
        Mockito.verify(tvRepository).setTvShowFavorit(dummyTvShow, true)

    }

    @Test
    fun `setFavorite should remove favorite from database`() {
        val dummyTvShow = DataCatalogue.generateCatalogueTVShows()[0]
        dummyTvShow.favorited = true
        doNothing().`when`(tvRepository).setTvShowFavorit(dummyTvShow, false)
        viewModel.setFavorite(dummyTvShow)
        Mockito.verify(tvRepository).setTvShowFavorit(dummyTvShow, false)

    }

    class PagedTestDataSources private constructor(private val items: List<TvsEntity>) : PositionalDataSource<TvsEntity>() {
        companion object {
            fun snapshot(items: List<TvsEntity> = listOf()): PagedList<TvsEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 11)
                        .setNotifyExecutor(Executors.newSingleThreadExecutor())
                        .setFetchExecutor(Executors.newSingleThreadExecutor())
                        .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TvsEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvsEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }

    }
}