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

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_MOVIE = 1
private const val HEADER = 1

class MoviesAdapter(
    private val movies: List<Movie>,
    private val onClickListener: OnRecyclerItemClicked
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class MoviesHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class MovieViewHolder(private var binding: ViewHolderMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                tvName.text = movie.name
                fillImage(movie.kinoposter, ivKinoposter)
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
                fillImage(R.drawable.like, ivLike)
                if (movie.liked) {
                    ivLike.setTint(R.color.tag)
                } else {
                    ivLike.setTint(R.color.white)
                }
            }
        }

        private fun ViewHolderMovieBinding.fillImage(
            resource: Int,
            viewId: ImageView
        ) {
            Glide.with(root)
                .load(resource)
                .into(viewId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> MoviesHeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.movies_item_header,
                    parent,
                    false
                )
            )
            ITEM_VIEW_TYPE_MOVIE -> MovieViewHolder(
                ViewHolderMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.apply {
                bind(movies[position - HEADER])
                itemView.setOnClickListener {
                    onClickListener.onClick(movies[position - HEADER])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size + HEADER
    }

    class OnRecyclerItemClicked(val clickListener: (movie: Movie) -> Unit) {
        fun onClick(movie: Movie) = clickListener(movie)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            HEADER_POSITION -> ITEM_VIEW_TYPE_HEADER
            else -> ITEM_VIEW_TYPE_MOVIE
        }
    }

    override fun getItemId(position: Int): Long {
        return movies[position - HEADER].id.toLong()
    }

    companion object {
        const val HEADER_POSITION = 0
    }
}

fun ImageView.setTint(@ColorRes colorRes: Int) {
    ImageViewCompat.setImageTintList(
        this,
        ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
    )
}
