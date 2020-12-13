package ru.zhek.androidacademyfundamentals2020.moviesList

import android.content.res.ColorStateList
import android.view.LayoutInflater
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

    class MovieViewHolder(private var binding: ViewHolderMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                tvName.text = movie.name
                Glide.with(root)
                    .load(movie.kinoposter)
                    .into(ivKinoposter)
                tvPg.text = root.resources.getString(R.string.pg, movie.pg)
                tvGenres.text = movie.genres
                ratingBar.rating = movie.rating.toFloat()
                tvReviews.text = this@MovieViewHolder.itemView.context.resources.getQuantityString(
//                tvReviews.text = root.resources.getQuantityString(
                    R.plurals.reviews,
                    movie.reviews,
                    movie.reviews
                )
                tvDuration.text = root.resources.getString(R.string.duration, movie.duration)
                Glide.with(root)
                    .load(R.drawable.like)
                    .into(ivLike)
                if (movie.liked) {
                    ivLike.setTint(R.color.tag)
                } else {
                    ivLike.setTint(R.color.white)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ViewHolderMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.apply {
            bind(movies[position])
            itemView.setOnClickListener {
                onClickListener.onClick(movies[position])
            }
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
