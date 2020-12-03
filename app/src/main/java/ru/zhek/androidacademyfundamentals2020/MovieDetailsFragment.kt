package ru.zhek.androidacademyfundamentals2020

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.zhek.androidacademyfundamentals2020.Domain.MoviesDataSource
import ru.zhek.androidacademyfundamentals2020.data.models.Movie

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var movies: List<Movie> = MoviesDataSource().getFilms()

//    TODO delete
//    private var backposter: ImageView? = null
//    private var pg: TextView? = null
//    private var name: TextView? = null
//    private var genres: TextView? = null
//    private var ratingBar: RatingBar? = null
//    private var reviews: TextView? = null
//    private var storyline_text: TextView? = null
//    private var actorsRecycler: RecyclerView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO
        val backposter: ImageView = view.findViewById<ImageView>(R.id.iv_backposter)
        val pg: TextView = view.findViewById<TextView>(R.id.tv_pg)
        val name: TextView = view.findViewById<TextView>(R.id.tv_name)
        val genres: TextView = view.findViewById<TextView>(R.id.tv_genres)
        val ratingBar: RatingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val reviews: TextView = view.findViewById<TextView>(R.id.tv_reviews)
        val storyline_text: TextView = view.findViewById<TextView>(R.id.tv_storyline_text)
//        TODO
//        private var actorsRecycler: RecyclerView = view.findViewById<ImageView>(R.id.iv_backposter)

        val movieId = arguments?.getInt(MOVIE_ID_FLAG)
//        TODO
        Toast.makeText(this.context, "movieId = $movieId", Toast.LENGTH_SHORT).show()
        val movie = movies.find { it.id == movieId }

        Glide.with(view.context)
            .load(movie?.backposter)
//            .fitCenter()
//            .centerCrop()
            .centerInside()
            .into(backposter)
        pg.text = "${movie?.pg}+"
            name.text = movie?.name
        genres.text = movie?.genres
        ratingBar.rating = movie?.rating?.toFloat() ?: 0f
        reviews.text = "${movie?.reviews} reviews"
        storyline_text.text = movie?.storyline
        //        TODO
//        actorsRecycler

        view.findViewById<TextView>(R.id.tv_back)
            .setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
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