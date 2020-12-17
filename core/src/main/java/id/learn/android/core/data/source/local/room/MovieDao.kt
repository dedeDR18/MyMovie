package id.learn.android.core.data.source.local.room

import androidx.room.*
import id.learn.android.core.data.source.local.entity.MovieEntitiy

import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentities")
    fun getAllMovies(): Flow<List<MovieEntitiy>>

    @Query("SELECT * FROM movieentities WHERE favorited = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntitiy>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntitiy>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntitiy)
}