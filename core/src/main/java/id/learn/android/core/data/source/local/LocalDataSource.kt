package id.learn.android.core.data.source.local

import id.learn.android.core.data.source.local.entity.MovieEntitiy
import id.learn.android.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {
    fun getAllMovies(): Flow<List<MovieEntitiy>> = movieDao.getAllMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntitiy>> = movieDao.getFavoriteMovies()

    suspend fun insertMovie(movieList: List<MovieEntitiy>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntitiy, newState: Boolean) {
        movie.favorited = newState
        movieDao.updateFavoriteMovie(movie)
    }
}