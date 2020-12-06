package ru.zhek.androidacademyfundamentals2020.movieDetails

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.data.models.Movie
import ru.zhek.androidacademyfundamentals2020.databinding.FragmentMovieDetailsBinding
import ru.zhek.androidacademyfundamentals2020.domain.MoviesDataSource

const val DEFAULT_MOVIE_ID: Int = 1

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var fragmentMovieDetailsBinding: FragmentMovieDetailsBinding? = null
    private var movieId: Int = arguments?.getInt(MOVIE_ID_FLAG) ?: DEFAULT_MOVIE_ID

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieDetailsBinding.bind(view)
        fragmentMovieDetailsBinding = binding

        initListComponent(binding)

        val movie = obtainMovie()

        fillViews(view, movie, binding)

        binding.tvBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun initListComponent(binding: FragmentMovieDetailsBinding) {
        binding.rvActors.adapter = ActorAdapter()

        val horizontalDecorator =
            DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL).apply {
                this.setDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.divider
                    )!!
                )
            }
        binding.rvActors.addItemDecoration(horizontalDecorator)
    }

    private fun obtainMovie(): Movie? {
        movieId = arguments?.getInt(MOVIE_ID_FLAG) ?: 1
        return MoviesDataSource().getFilms().find { it.id == movieId }
    }

    private fun fillViews(
        view: View,
        movie: Movie?,
        binding: FragmentMovieDetailsBinding
    ) {
        if (movie != null) {
            Glide.with(view.context)
                .load(movie.backposter)
                .centerInside()
                .into(binding.ivBackposter)
            binding.tvPg.text = getString(R.string.pg, movie.pg)
            binding.tvName.text = movie.name
            binding.tvGenres.text = movie.genres
            binding.ratingBar.rating = movie.rating.toFloat()
            binding.tvReviews.text =
                resources.getQuantityString(R.plurals.reviews, movie.reviews, movie.reviews)
            binding.tvStorylineText.text = movie.storyline
        }
    }

    override fun onStart() {
        super.onStart()

        updateActorsData()
    }

    private fun updateActorsData() {
        (fragmentMovieDetailsBinding?.rvActors?.adapter as? ActorAdapter)?.apply {
            bindActors(
                MoviesDataSource().getFilms()
                    .find { it.id == movieId }!!
                    .castList
                    .shuffled()
            )
        }
    }

    override fun onDestroyView() {
        fragmentMovieDetailsBinding = null
        super.onDestroyView()
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
