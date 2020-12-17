package id.learn.android.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.learn.android.core.R
import id.learn.android.core.databinding.ItemListMovieBinding
import id.learn.android.core.domain.model.Movie
import java.util.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListMovieViewHolder>() {

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
    ): MovieAdapter.ListMovieViewHolder = ListMovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))

    override fun onBindViewHolder(holder: MovieAdapter.ListMovieViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemListMovieBinding.bind(itemView)
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