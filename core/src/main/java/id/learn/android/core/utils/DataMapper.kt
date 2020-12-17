package id.learn.android.core.utils

import id.learn.android.core.data.source.local.entity.MovieEntitiy
import id.learn.android.core.data.source.remote.response.MovieResponse
import id.learn.android.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntitiy> {
        val movieList = ArrayList<MovieEntitiy>()
        input.map {
            val movie = MovieEntitiy(
                idMovie = it.idMovie,
                popularity = it.popularity,
                originalTitle = it.originalTitle,
                originalLanguage = it.originalLanguage,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                overview = it.overview,
                voteAvarage = it.voteAvarage,
                favorited = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntitiy>) : List<Movie> =
        input.map {
            Movie(
                idMovie = it.idMovie,
                popularity = it.popularity,
                originalTitle = it.originalTitle,
                originalLanguage = it.originalLanguage,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                overview = it.overview,
                voteAvarage = it.voteAvarage,
                isFavorite = it.favorited
            )
        }

    fun mapDomainToEntities(input: Movie) = MovieEntitiy(
        idMovie = input.idMovie,
        popularity = input.popularity,
        originalTitle = input.originalTitle,
        originalLanguage = input.originalLanguage,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        overview = input.overview,
        voteAvarage = input.voteAvarage,
        favorited = input.isFavorite
    )
}