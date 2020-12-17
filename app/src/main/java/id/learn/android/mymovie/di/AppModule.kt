package id.learn.android.mymovie.di

import id.learn.android.core.domain.usecase.MovieInteractor
import id.learn.android.core.domain.usecase.MovieUseCase
import id.learn.android.mymovie.MainViewModel
import id.learn.android.mymovie.detail.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get())}
    viewModel { DetailViewModel(get())}
}