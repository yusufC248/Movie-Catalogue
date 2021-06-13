package com.submission.yusuf.movie


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.submission.yusuf.data.source.FilmRepository
import com.submission.yusuf.data.source.local.entity.FilmEntity
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
class MovieViewModelTest{
    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var filmRepository: FilmRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<FilmEntity>>>



    @Before
    fun setUp(){
        viewModel = MovieViewModel(filmRepository)

    }

    @Test
    fun `returnMovie should be success`(){
        val movies = PagedTestDataSources.snapshot(DataCatalogue.generateCatalogueFilm())
        val expected = MutableLiveData<Resource<PagedList<FilmEntity>>>()
        expected.value = Resource.success(movies)
        `when`(filmRepository.getAllMovie()).thenReturn(expected)
        viewModel.returnMovie().observeForever(observer)
        verify(observer).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.returnMovie().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)

    }
    
    @Test
    fun `returnMovie should be success but data is empty`(){
        val movies = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<FilmEntity>>>()
        expected.value = Resource.success(movies)

        `when`(filmRepository.getAllMovie()).thenReturn(expected)

        viewModel.returnMovie().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.returnMovie().value?.data?.size
        Assert.assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)

    }

    @Test
    fun `returnMovie should be error`(){
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<Resource<PagedList<FilmEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        `when`(filmRepository.getAllMovie()).thenReturn(expected)

        viewModel.returnMovie().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualMessage = viewModel.returnMovie().value?.message
        assertEquals(expectedMessage, actualMessage)
    }

    class PagedTestDataSources private constructor(private val items: List<FilmEntity>) : PositionalDataSource<FilmEntity>() {
        companion object {
            fun snapshot(items: List<FilmEntity> = listOf()): PagedList<FilmEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
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