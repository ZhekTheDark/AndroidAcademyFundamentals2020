package ru.zhek.androidacademyfundamentals2020.movieDetails

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.data.Movie
import ru.zhek.androidacademyfundamentals2020.data.loadMovies
import ru.zhek.androidacademyfundamentals2020.databinding.FragmentMovieDetailsBinding
import kotlin.math.round

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("CoroutineExceptionHandler got $exception in $coroutineContext")
    }
    private val job = Job()
    private val scope: CoroutineScope = CoroutineScope(
        Dispatchers.Main + job + exceptionHandler
    )
    lateinit var movie: Movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailsBinding.bind(view)

        val movieId = arguments!!.getInt(MOVIE_ID_FLAG)

        scope.launch {
            movie = obtainMovieAsync(movieId).await()
            fillViews(movie)
            if (movie.actors.isNotEmpty()) initListComponent(movie)
        }

        binding.tvBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun obtainMovieAsync(id: Int): Deferred<Movie> = scope.async {
        val movies = loadMovies(requireContext())
        movies.find { it.id == id }!!
    }


    private fun fillViews(movie: Movie) {
        binding.apply {
            tvPg.text = getString(R.string.pg, movie.minimumAge)
            tvName.text = movie.title
            tvGenres.text = movie.genres.joinToString { it.name }
            ratingBar.rating = round(movie.ratings) / 2
            tvReviews.text =
                resources.getQuantityString(
                    R.plurals.reviews,
                    movie.numberOfRatings,
                    movie.numberOfRatings
                )
            tvStorylineText.text = movie.overview
            Glide.with(requireContext())
                .load(movie.backdrop)
                .fitCenter()
                .into(ivBackposter)
        }
    }

    private fun initListComponent(movie: Movie) {
        binding.tvActor.visibility = View.VISIBLE
        binding.rvActors.apply {
            setHasFixedSize(true)

            adapter = ActorAdapter(movie.actors)

            val horizontalDecorator =
                DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL).apply {
                    this.setDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.divider
                        )!!
                    )
                }
            addItemDecoration(horizontalDecorator)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val MOVIE_DETAILS_FRAGMENT_FLAG = "movieDetailsFragment"
        const val MOVIE_ID_FLAG = "movieIdFlag"

        fun newInstance(id: Int): MovieDetailsFragment {
            return MovieDetailsFragment().apply {
                val args = Bundle()

                args.putInt(MOVIE_ID_FLAG, id)
                arguments = args
            }
        }
    }
}
