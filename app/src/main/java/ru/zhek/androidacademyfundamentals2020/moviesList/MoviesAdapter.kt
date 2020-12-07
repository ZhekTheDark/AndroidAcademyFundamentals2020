package ru.zhek.androidacademyfundamentals2020.moviesList

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.data.models.Movie
import ru.zhek.androidacademyfundamentals2020.databinding.ViewHolderMovieBinding

class MoviesAdapter(
    private val movies: List<Movie>,
    private val onClickListener: OnRecyclerItemClicked
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewHolderMovieBinding.bind(itemView)

        fun bind(movie: Movie) {
            binding.tvName.text = movie.name
            Glide.with(this.itemView.context)
                .load(movie.kinoposter)
                .into(binding.ivKinoposter)
            binding.tvPg.text = this.itemView.context.getString(R.string.pg, movie.pg)
            binding.tvGenres.text = movie.genres
            binding.ratingBar.rating = movie.rating.toFloat()
            binding.tvReviews.text = this.itemView.context.resources.getQuantityString(
                R.plurals.reviews,
                movie.reviews,
                movie.reviews
            )
            binding.tvDuration.text =
                this.itemView.context.getString(R.string.duration, movie.duration)
            Glide.with(this.itemView.context)
                .load(R.drawable.like)
                .into(binding.ivLike)
            if (movie.liked) {
                binding.ivLike.setTint(R.color.tag)
            } else {
                binding.ivLike.setTint(R.color.white)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            onClickListener.onClick(movies[position])
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class OnRecyclerItemClicked(val clickListener: (movie: Movie) -> Unit) {
        fun onClick(movie: Movie) = clickListener(movie)
    }
}

fun ImageView.setTint(@ColorRes colorRes: Int) {
    ImageViewCompat.setImageTintList(
        this,
        ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
    )
}
