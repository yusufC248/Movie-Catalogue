package com.submission.yusuf.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithModule(
        @Embedded
        var mMovie: FilmEntity,
        @Relation(parentColumn = "filmId", entityColumn = "filmId")
        var mModules: List<ModuleEntity>
)
