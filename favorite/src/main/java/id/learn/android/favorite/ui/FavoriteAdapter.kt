package id.learn.android.favorite.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.learn.android.core.domain.model.Movie
import id.learn.android.favorite.R
import id.learn.android.favorite.databinding.ItemListFavMovieBinding
import java.util.ArrayList

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ListFavoriteMovieViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(listMovie: List<Movie>?){
        if (listMovie == null) return
        listData.clear()
        listData.addAll(listMovie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.ListFavoriteMovieViewHolder = ListFavoriteMovieViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_fav_movie, parent, false))

    override fun onBindViewHolder(holder: FavoriteAdapter.ListFavoriteMovieViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListFavoriteMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemListFavMovieBinding.bind(itemView)
        fun bind(data: Movie){
            with(binding){
                tvItemTitle.text = data.originalTitle
                tvItemDate.text = data.releaseDate
                tvItemPopularity.text = data.popularity.toString()
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2"+data.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_base_image)
                            .error(R.drawable.ic_base_broken_image)
                    )
                    .into(imgPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}