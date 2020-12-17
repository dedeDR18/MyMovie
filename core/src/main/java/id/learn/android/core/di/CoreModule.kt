package id.learn.android.core.di

import androidx.room.Room
import id.learn.android.core.data.MovieRepository
import id.learn.android.core.data.source.local.LocalDataSource
import id.learn.android.core.data.source.local.room.MovieDatabase
import id.learn.android.core.data.source.remote.RemoteDataSource
import id.learn.android.core.data.source.remote.network.ApiService
import id.learn.android.core.domain.repository.IMovieRepository
import id.learn.android.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<MovieDatabase>().movieDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "Movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory {
        AppExecutors()
    }
    single<IMovieRepository> { MovieRepository(get(), get(), get())}
}