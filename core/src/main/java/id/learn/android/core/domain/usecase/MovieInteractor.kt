package id.learn.android.core.domain.usecase

import id.learn.android.core.data.Resource
import id.learn.android.core.domain.model.Movie
import id.learn.android.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> = movieRepository.getAllMovies()


    override fun getFavoriteMovies(): Flow<List<Movie>> = movieRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)
}