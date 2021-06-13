package com.submission.yusuf.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvsResponse(
    var filmId: String,
    var title: String,
    var description: String,
    var imagePath: String,
    var background: String,
    var rating: String
): Parcelable
