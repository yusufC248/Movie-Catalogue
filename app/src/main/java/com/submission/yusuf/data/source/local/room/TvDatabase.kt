package com.submission.yusuf.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.submission.yusuf.data.source.local.entity.FilmEntity
import com.submission.yusuf.data.source.local.entity.ModuleEntity
import com.submission.yusuf.data.source.local.entity.TvsEntity

@Database(entities = [FilmEntity::class, TvsEntity::class, ModuleEntity::class], version = 1, exportSchema = false)
abstract class TvDatabase: RoomDatabase() {
    abstract fun tvDao(): TvDao

    companion object {

        @Volatile
        private var INSTANCE: TvDatabase? = null

        fun getInstance(context: Context): TvDatabase =
                INSTANCE ?: synchronized(this) {
                    Room.databaseBuilder(
                            context.applicationContext,
                            TvDatabase::class.java,
                            "Tv.db"
                    ).build().apply {
                        INSTANCE = this
                    }
                }
    }
}