package com.submission.yusuf.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.submission.yusuf.data.source.TvRepository
import com.submission.yusuf.data.source.local.entity.TvsEntity
import com.submission.yusuf.utils.DataCatalogue
import com.submission.yusuf.vo.Resource
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
class TvShowViewModelTest{

    private lateinit var viewModel: TvShowViewModel

    @Mock
    private lateinit var tvRepository: TvRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvsEntity>>>



    @Before
    fun setUp(){
        viewModel = TvShowViewModel(tvRepository)
    }

    @Test
    fun `testReturnTvShow should be success`() {
        val tvshow = PagedTestDataSources.snapshot(DataCatalogue.generateCatalogueTVShows())
        val expected = MutableLiveData<Resource<PagedList<TvsEntity>>>()
        expected.value = Resource.success(tvshow)
        `when`(tvRepository.getAllTvShow()).thenReturn(expected)
        viewModel.returnTvShow().observeForever(observer)
        verify(observer).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.returnTvShow().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `returnTvShow should be success but data is empty`(){
        val tvshow = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<TvsEntity>>>()
        expected.value = Resource.success(tvshow)

        `when`(tvRepository.getAllTvShow()).thenReturn(expected)

        viewModel.returnTvShow().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.returnTvShow().value?.data?.size
        Assert.assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)

    }

    @Test
    fun `returnTvShow should be error `(){
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<Resource<PagedList<TvsEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        `when`(tvRepository.getAllTvShow()).thenReturn(expected)

        viewModel.returnTvShow().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualMessage = viewModel.returnTvShow().value?.message
        assertEquals(expectedMessage, actualMessage)
    }

    class PagedTestDataSources private constructor(private val items: List<TvsEntity>) : PositionalDataSource<TvsEntity>() {
        companion object {
            fun snapshot(items: List<TvsEntity> = listOf()): PagedList<TvsEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
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