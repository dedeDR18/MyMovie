package id.learn.android.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieEntitiy(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idMovie")
    var idMovie: Long?,

    @ColumnInfo(name = "popularity")
    var popularity: Float? = null,

    @ColumnInfo(name = "originalTitle")
    var originalTitle: String? = null,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String? = null,

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "voteAvarage")
    var voteAvarage: Float? = null,

    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false

)