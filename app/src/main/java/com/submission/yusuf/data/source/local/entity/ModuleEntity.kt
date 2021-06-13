package com.submission.yusuf.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.*


@Entity(tableName="moduleentities",
    primaryKeys = ["moduleId", "filmId"],
    foreignKeys = [ForeignKey(entity = FilmEntity::class,
    parentColumns = ["filmId"],
    childColumns = ["filmId"])],
    indices = [Index(value =  ["moduleId"]),
        Index(value = ["filmId"])])
data class ModuleEntity(
        @NonNull
        @ColumnInfo(name = "moduleId")
        var moduleId: String,

        @NonNull
        @ColumnInfo(name = "filmId")
        var filmId: String,

        @NonNull
        @ColumnInfo(name = "title")
        var title: String,

        @NonNull
        @ColumnInfo(name = "position")
        var position: Int,

        @ColumnInfo(name = "read")
        var read: Boolean = false
){
    @Embedded
    var contentEntity: ContentEntity? = null
}