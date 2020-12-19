package ru.zhek.androidacademyfundamentals2020.movieDetails

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.data.MoviesDataSource
import ru.zhek.androidacademyfundamentals2020.data.models.Movie
import ru.zhek.androidacademyfundamentals2020.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailsBinding.bind(view)

        val movieId = arguments?.getInt(MOVIE_ID_FLAG) ?: MoviesDataSource().getFilms().first().id
        val movie: Movie = obtainMovie(movieId)

        fillViews(movie)

        initListComponent(movie)

        binding.tvBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun obtainMovie(id: Int): Movie {
        return MoviesDataSource().getFilms().find { it.id == id }!!
    }

    private fun fillViews(movie: Movie) {
        binding.apply {
            tvPg.text = getString(R.string.pg, movie.pg)
            tvName.text = movie.name
            tvGenres.text = movie.genres
            ratingBar.rating = movie.rating.toFloat()
            tvReviews.text =
                resources.getQuantityString(R.plurals.reviews, movie.reviews, movie.reviews)
            tvStorylineText.text = movie.storyline
            Glide.with(requireContext())
                .load(movie.backposter)
                .centerInside()
                .into(ivBackposter)
        }
    }

    private fun initListComponent(movie: Movie) {
        binding.rvActors.apply {
            setHasFixedSize(true)

            adapter = ActorAdapter(
                movie.castList.shuffled()
            )

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
