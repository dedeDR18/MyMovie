package id.learn.android.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    @field:SerializedName("id")
    val idMovie: Long? = null,
    @field:SerializedName("popularity")
    val popularity: Float? = null,
    @field:SerializedName("original_title")
    val originalTitle: String? = null,
    @field:SerializedName("original_language")
    val originalLanguage: String? = null,
    @field:SerializedName("poster_path")
    val posterPath: String? = null,
    @field:SerializedName("release_date")
    val releaseDate: String? = null,
    @field:SerializedName("overview")
    val overview: String? = null,
    @field:SerializedName("vote_average")
    val voteAvarage: Float? = null
) : Parcelable