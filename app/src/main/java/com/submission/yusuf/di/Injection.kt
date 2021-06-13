package com.submission.yusuf.di

import android.content.Context
import com.submission.yusuf.data.source.FilmRepository
import com.submission.yusuf.data.source.TvRepository
import com.submission.yusuf.data.source.local.LocalDataSource
import com.submission.yusuf.data.source.local.TvLocalDatSource
import com.submission.yusuf.data.source.local.room.FilmDatabase
import com.submission.yusuf.data.source.local.room.TvDatabase
import com.submission.yusuf.data.source.remote.RemoteFilmSource
import com.submission.yusuf.utils.AppExecutors
import com.submission.yusuf.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): FilmRepository{
        val database = FilmDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val remoteFilmSource = RemoteFilmSource.getInstance(JsonHelper(context))
        val appExecutors = AppExecutors()
        return FilmRepository.getInstance(remoteFilmSource, localDataSource, appExecutors)
    }

    fun tvshowProfideRepository(context: Context): TvRepository{
        val database = TvDatabase.getInstance(context)
        val localDataSource = TvLocalDatSource.getInstance(database.tvDao())
        val remoteFilmSource = RemoteFilmSource.getInstance(JsonHelper(context))
        val appExecutors = AppExecutors()
        return TvRepository.getInstance(remoteFilmSource, localDataSource, appExecutors)
    }
}