package id.learn.android.core.data

import id.learn.android.core.data.source.local.LocalDataSource
import id.learn.android.core.data.source.remote.RemoteDataSource
import id.learn.android.core.data.source.remote.network.ApiResponse
import id.learn.android.core.data.source.remote.response.MovieResponse
import id.learn.android.core.domain.model.Movie
import id.learn.android.core.domain.repository.IMovieRepository
import id.learn.android.core.utils.AppExecutors
import id.learn.android.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(){
        override fun loadFromDB(): Flow<List<Movie>> {
            return localDataSource.getAllMovies().map {
                DataMapper.mapEntitiesToDomain(it)
            }
        }

        override fun shouldFetch(data: List<Movie>?): Boolean =
            data == null || data.isEmpty()


        override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
            return remoteDataSource.getAllMovies()
        }

        override suspend fun saveCallResult(data: List<MovieResponse>) {
            val movieList = DataMapper.mapResponsesToEntities(data)
            localDataSource.insertMovie(movieList)
        }
    }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntities(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

}