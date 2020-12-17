package id.learn.android.core.domain.usecase

import id.learn.android.core.data.Resource
import id.learn.android.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}