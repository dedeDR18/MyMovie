package id.learn.android.mymovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.learn.android.core.domain.usecase.MovieUseCase

class MainViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val movies = movieUseCase.getAllMovies().asLiveData()
}