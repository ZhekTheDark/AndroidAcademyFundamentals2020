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

const val DEFAULT_MOVIE_ID: Int = 1

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private var movieId: Int = arguments?.getInt(MOVIE_ID_FLAG) ?: DEFAULT_MOVIE_ID

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailsBinding.bind(view)

        initListComponent()

        val movie: Movie? = obtainMovie()

        fillViews(movie)

        binding.tvBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun initListComponent() {
        binding.rvActors.apply {
            adapter = ActorAdapter(
                MoviesDataSource().getFilms()
                    .find { it.id == movieId }!!
                    .castList
                    .shuffled()
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

    private fun obtainMovie(): Movie? {
        movieId = arguments?.getInt(MOVIE_ID_FLAG) ?: DEFAULT_MOVIE_ID
        return MoviesDataSource().getFilms().find { it.id == movieId }
    }

    private fun fillViews(movie: Movie?) {
        if (movie != null) {
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
