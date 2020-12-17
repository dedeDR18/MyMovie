package id.learn.android.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    val idMovie: Long?,
    val popularity: Float?,

    val originalTitle: String?,

    val originalLanguage: String?,

    val posterPath: String?,

    val releaseDate: String?,

    val overview: String?,

    val voteAvarage: Float?,

    val isFavorite: Boolean
) : Parcelable