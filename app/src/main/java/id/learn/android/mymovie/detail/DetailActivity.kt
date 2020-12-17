package id.learn.android.mymovie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.learn.android.core.domain.model.Movie
import id.learn.android.mymovie.R
import id.learn.android.mymovie.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private var menu: Menu? = null
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.activity_detail_film)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        movie = detailMovie!!


        binding.pgDetail.visibility = View.VISIBLE
        showData(detailMovie)
    }

    private fun showData(data: Movie?) {
        data?.let { movie ->
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movie.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_base_image)
                        .error(R.drawable.ic_base_broken_image)
                )
                .into(binding.ivPosterMovie)
            binding.titlee.text = movie.originalTitle
            binding.dateRelease.text = movie.releaseDate
            binding.vote.text = movie.voteAvarage.toString()
            binding.language.text = movie.originalLanguage
            binding.popularity.text = movie.popularity.toString()
            binding.overview.text = movie.overview
            binding.pgDetail.visibility = View.GONE
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
        setFavoriteState(movie.isFavorite)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
        } else if (item.itemId == R.id.detail_fav_menu){
            setFavoriteState(!movie.isFavorite)
            detailViewModel.setFavoriteMovie(movie)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean){
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.detail_fav_menu)
        if (state){
            menuItem?.icon = ContextCompat.getDrawable(this,R.drawable.ic_favorited)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        }
    }
}