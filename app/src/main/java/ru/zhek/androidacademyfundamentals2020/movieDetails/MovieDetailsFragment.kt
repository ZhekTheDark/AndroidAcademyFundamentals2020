package ru.zhek.androidacademyfundamentals2020.movieDetails

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.data.models.Movie
import ru.zhek.androidacademyfundamentals2020.domain.MoviesDataSource

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var movies: List<Movie> = MoviesDataSource().getFilms()
    private var actorsRecycler: RecyclerView? = null
    private var movieId: Int = arguments?.getInt(MOVIE_ID_FLAG) ?: 1

    override fun onStart() {
        super.onStart()

// TODO: 04.12.2020 mne ne och nravirsya, mb peredat' parametr i bydet ok?
        updateActorsData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backposter = view.findViewById<ImageView>(R.id.iv_backposter)
        val pg = view.findViewById<TextView>(R.id.tv_pg)
        val name = view.findViewById<TextView>(R.id.tv_name)
        val genres = view.findViewById<TextView>(R.id.tv_genres)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val reviews = view.findViewById<TextView>(R.id.tv_reviews)
        val storyline = view.findViewById<TextView>(R.id.tv_storyline_text)
        actorsRecycler = view.findViewById(R.id.rv_actors)
        actorsRecycler?.adapter = ActorAdapter()

        val horizontalDecorator =
            DividerItemDecoration(this.context, DividerItemDecoration.HORIZONTAL)
        horizontalDecorator.setDrawable(
            ContextCompat.getDrawable(
                this.context!!,
                R.drawable.divider
            )!!
        )
        actorsRecycler?.addItemDecoration(horizontalDecorator)

        movieId = arguments?.getInt(MOVIE_ID_FLAG) ?: 1
        val movie = movies.find { it.id == movieId }

        Glide.with(view.context)
            .load(movie?.backposter)
            .centerInside()
            .into(backposter)
        pg.text = getString(R.string.pg, movie?.pg)
        name.text = movie?.name
        genres.text = movie?.genres
        ratingBar.rating = movie?.rating?.toFloat() ?: 0f
        reviews.text = getString(R.string.reviews, movie?.reviews)
        storyline.text = movie?.storyline
        actorsRecycler

        view.findViewById<TextView>(R.id.tv_back)
            .setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
            }
    }

    private fun updateActorsData() {
        (actorsRecycler?.adapter as? ActorAdapter)?.apply {
            bindActors(
                MoviesDataSource().getFilms()
                    .find { it.id == movieId }!!
                    .castList
                    .shuffled()
            )
        }
    }

    companion object {
        const val MOVIE_DETAILS_FRAGMENT_FLAG = "movieDetailsFragment"
        const val MOVIE_ID_FLAG = "movieIdFlag"

        fun newInstance(id: Int): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val args = Bundle()

            args.putInt(MOVIE_ID_FLAG, id)
            fragment.arguments = args

            return fragment
        }
    }
}
