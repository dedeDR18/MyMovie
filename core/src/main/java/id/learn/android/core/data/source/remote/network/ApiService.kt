package id.learn.android.core.data.source.remote.network

import id.learn.android.core.data.source.remote.response.TmdbMovieResponse
import retrofit2.http.GET


interface ApiService {
    @GET("discover/movie?api_key=b54ae81b398a5890951b1449f680b6e3&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    suspend fun getMovieDiscover(): TmdbMovieResponse


}