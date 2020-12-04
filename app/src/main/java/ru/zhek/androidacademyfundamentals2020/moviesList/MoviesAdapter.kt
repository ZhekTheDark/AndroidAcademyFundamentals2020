package ru.zhek.androidacademyfundamentals2020.moviesList

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.data.models.Movie
import ru.zhek.androidacademyfundamentals2020.ui.custom.RatingBar

class MoviesAdapter(
    private val onClickListener: OnRecyclerItemClicked
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var movies = listOf<Movie>()

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.findViewById<TextView>(R.id.tv_name)
        private val kinoposter = itemView.findViewById<ImageView>(R.id.iv_kinoposter)
        private val pg = itemView.findViewById<TextView>(R.id.tv_pg)
        private val genres = itemView.findViewById<TextView>(R.id.tv_genres)
        private val rating = itemView.findViewById<RatingBar>(R.id.ratingBar)
        private val reviews = itemView.findViewById<TextView>(R.id.tv_reviews)
        private val duration = itemView.findViewById<TextView>(R.id.tv_duration)
        private val like = itemView.findViewById<ImageView>(R.id.iv_like)

        fun bind(movie: Movie) {
            name.text = movie.name
            Glide.with(this.itemView.context)
                .load(movie.kinoposter)
                .into(kinoposter)
//            TODO
            pg.text = "${movie.pg}+"
            genres.text = movie.genres
            rating.rating = movie.rating.toFloat()
            reviews.text = "${movie.reviews} reviews"
            duration.text = "${movie.duration} min"

            Glide.with(this.itemView.context)
                .load(R.drawable.like)
                .into(like)
            if (movie.liked) {
                like.setTint(R.color.tag)
            } else {
                like.setTint(R.color.white)
            }

            itemView.setOnClickListener { this.layoutPosition }
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

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}

interface OnRecyclerItemClicked {
    fun onClick(movie: Movie)
}

fun ImageView.setTint(@ColorRes colorRes: Int) {
    ImageViewCompat.setImageTintList(
        this,
        ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
    )
}
