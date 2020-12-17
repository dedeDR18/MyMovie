package id.learn.android.mymovie.detail

import androidx.lifecycle.ViewModel
import id.learn.android.core.domain.model.Movie
import id.learn.android.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    fun setFavoriteMovie(movie: Movie) = movieUseCase.setFavoriteMovie(movie, !movie.isFavorite)

}