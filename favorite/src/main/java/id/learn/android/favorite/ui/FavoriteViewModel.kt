package id.learn.android.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.learn.android.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase): ViewModel() {

    val favMovie = movieUseCase.getFavoriteMovies().asLiveData()
}