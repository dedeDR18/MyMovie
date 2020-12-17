package id.learn.android.mymovie

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.learn.android.core.data.Resource
import id.learn.android.core.ui.MovieAdapter
import id.learn.android.mymovie.databinding.ActivityMainBinding
import id.learn.android.mymovie.detail.DetailActivity
import id.learn.android.mymovie.detail.DetailActivity.Companion.EXTRA_DATA
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel:MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieAdapter = MovieAdapter()

        movieAdapter.onItemClick = { movie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(EXTRA_DATA, movie)
            startActivity(intent)
        }

        binding.root.pgMain.visibility = View.VISIBLE
        mainViewModel.movies.observe(this, { movie ->
            if (movie != null){
                when (movie){
                    is Resource.Loading -> binding.root.pgMain.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.root.pgMain.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.root.pgMain.visibility = View.GONE
                        Toast.makeText(this, getString(R.string.main_error), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(rvMovie){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.main_fav_menu){
            val uri = Uri.parse("mymovie://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        return super.onOptionsItemSelected(item)
    }

}