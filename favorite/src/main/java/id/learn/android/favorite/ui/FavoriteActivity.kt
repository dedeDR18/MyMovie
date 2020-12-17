package id.learn.android.favorite.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import id.learn.android.favorite.databinding.ActivityFavoriteBinding
import id.learn.android.favorite.di.favoriteModule
import id.learn.android.mymovie.R
import id.learn.android.mymovie.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_favorite)

        loadKoinModules(favoriteModule)

        favoriteAdapter = FavoriteAdapter()

        getFavoriteMovie()

        favoriteAdapter.onItemClick = { movie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, movie)
            startActivity(intent)
        }

        with(binding.idContentFav.rvFavMovie){
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }

    private fun getFavoriteMovie(){
        binding.idContentFav.pgFavorite.visibility = View.VISIBLE
        favoriteViewModel.favMovie.observe(this, { favMovie ->
            favMovie?.let { list ->
                favoriteAdapter.setData(favMovie)
                binding.idContentFav.pgFavorite.visibility = View.GONE
                binding.viewEmptyData.root.visibility = View.GONE
                if (list.isEmpty()){
                    binding.viewEmptyData.root.visibility = View.VISIBLE
                }
            }
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}