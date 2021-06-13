package com.submission.yusuf.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TvShowWithModule(
        @Embedded
        var mCourse: TvsEntity,
        @Relation(parentColumn = "filmId", entityColumn = "filmId")
        var mModules: List<ModuleEntity>
)
