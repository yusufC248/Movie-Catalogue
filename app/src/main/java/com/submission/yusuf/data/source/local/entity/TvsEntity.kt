package com.submission.yusuf.data.source.local.entity


import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize



@Entity(tableName = "tvshowentities")
@Parcelize
data class TvsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "filmId")
    var filmId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name ="imagePath")
    var imagePath: String,

    @ColumnInfo(name = "background")
    var background: String,

    @ColumnInfo(name = "rating")
    var rating: String,


    @NonNull
    @ColumnInfo(name ="favorited")
    var favorited: Boolean = false
): Parcelable
