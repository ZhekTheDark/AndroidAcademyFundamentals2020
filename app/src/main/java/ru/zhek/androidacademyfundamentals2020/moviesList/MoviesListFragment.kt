package ru.zhek.androidacademyfundamentals2020.moviesList

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.data.models.Movie
import ru.zhek.androidacademyfundamentals2020.domain.MoviesDataSource
import ru.zhek.androidacademyfundamentals2020.movieDetails.MovieDetailsFragment


class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private var moviesRecycler: RecyclerView? = null

    override fun onStart() {
        super.onStart()

        updateMoviesData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesRecycler = view.findViewById(R.id.rv_movies)
        moviesRecycler?.adapter = MoviesAdapter(clickListener)

        val verticalDecorator = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        val horizontalDecorator =
            DividerItemDecoration(this.context, DividerItemDecoration.HORIZONTAL)
        verticalDecorator.setDrawable(
            ContextCompat.getDrawable(
                this.context!!,
                R.drawable.divider
            )!!
        )
        horizontalDecorator.setDrawable(
            ContextCompat.getDrawable(
                this.context!!,
                R.drawable.divider
            )!!
        )
        moviesRecycler?.addItemDecoration(verticalDecorator)
        moviesRecycler?.addItemDecoration(horizontalDecorator)
    }

    private fun updateMoviesData() {
        (moviesRecycler?.adapter as? MoviesAdapter)?.apply {
            bindMovies(MoviesDataSource().getFilms())
        }
    }

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(movie: Movie) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .setCustomAnimations(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right
                    )
                    .addToBackStack(null)
                    .replace(
                        R.id.fragments_container,
                        MovieDetailsFragment.newInstance(movie.id),
                        MovieDetailsFragment.MOVIE_DETAILS_FRAGMENT_FLAG
                    )
                    .commit()
            }
        }
    }

    companion object {
        const val MOVIES_LIST_FRAGMENT_FLAG = "moviesListFragment"

        fun newInstance(): MoviesListFragment {
            val fragment = MoviesListFragment()
            return fragment
        }
    }
}
