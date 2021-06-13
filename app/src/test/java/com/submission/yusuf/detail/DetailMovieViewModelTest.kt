package com.submission.yusuf.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.submission.yusuf.data.source.FilmRepository
import com.submission.yusuf.data.source.local.entity.MovieWithModule
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
class DetailMovieViewModelTest{
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataCatalogue.generateCatalogueFilm()[0]
    private val movieId = dummyMovie.filmId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Resource<MovieWithModule>>

   @Before
   fun setUp(){
       viewModel = DetailMovieViewModel(filmRepository)
       viewModel.setSelectedCourse(movieId)
   }

    @Test
    fun `getMovieWithModuleTest should be success`(){

        val expected =  MutableLiveData<Resource<MovieWithModule>>()
        expected.value = Resource.success(DataCatalogue.generateDummyMovieWithModules(dummyMovie, true))
        `when`(filmRepository.getMovieWithModules(movieId)).thenReturn(expected)
        viewModel.movieModule.observeForever(observer)
        verify(observer).onChanged(expected.value)
        val expectedValue = expected.value
        val actualvalue = viewModel.movieModule.value
        assertEquals(expectedValue, actualvalue)
    }

    @Test
    fun `setFavorite should not add favorite when movieModule is empty`(){
       viewModel.setBookmark()
        verify(filmRepository, times(0)).setMovieFavorit(dummyMovie, true)

    }

    @Test
    fun `setFavorite should add favorite when movieModule is not empty and not favorited`(){
        val expected = MutableLiveData<Resource<MovieWithModule>>()
        expected.value = Resource.success(DataCatalogue.generateDummyMovieWithModules(dummyMovie, false))

        `when`(filmRepository.getMovieWithModules(movieId)).thenReturn(expected)
        viewModel.movieModule.observeForever(observer)

        doNothing().`when`(filmRepository).setMovieFavorit(dummyMovie, true)

        viewModel.setBookmark()
        verify(filmRepository, times(1)).setMovieFavorit(dummyMovie, true)

    }

    @Test
    fun `setFavorite should remove from favorite when movieModule is not Empty and already favorited`(){
        val expected = MutableLiveData<Resource<MovieWithModule>>()
        expected.value = Resource.success(DataCatalogue.generateDummyMovieWithModules(dummyMovie, true))

        `when`(filmRepository.getMovieWithModules(movieId)).thenReturn(expected)
        viewModel.movieModule.observeForever(observer)

        doNothing().`when`(filmRepository).setMovieFavorit(dummyMovie, false)

        viewModel.setBookmark()
        verify(filmRepository, times(1)).setMovieFavorit(dummyMovie, false)

    }
}