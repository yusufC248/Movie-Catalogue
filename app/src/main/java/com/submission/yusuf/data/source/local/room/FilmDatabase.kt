package com.submission.yusuf.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.submission.yusuf.data.source.local.entity.FilmEntity
import com.submission.yusuf.data.source.local.entity.ModuleEntity
import com.submission.yusuf.data.source.local.entity.TvsEntity

@Database(entities = [FilmEntity::class, TvsEntity::class, ModuleEntity::class], version = 2, exportSchema = false)
abstract class FilmDatabase : RoomDatabase(){
    abstract fun filmDao(): FilmDao

    companion object{
        @Volatile
        private var INSTANCE : FilmDatabase? = null

        fun getInstance(context: Context): FilmDatabase  =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                context.applicationContext,
                FilmDatabase::class.java,
                "movie.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}