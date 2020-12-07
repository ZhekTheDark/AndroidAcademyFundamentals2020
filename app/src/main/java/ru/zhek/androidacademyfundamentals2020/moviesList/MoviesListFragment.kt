package ru.zhek.androidacademyfundamentals2020.moviesList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.data.MoviesDataSource
import ru.zhek.androidacademyfundamentals2020.databinding.FragmentMoviesListBinding
import ru.zhek.androidacademyfundamentals2020.movieDetails.MovieDetailsFragment

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private var fragmentMoviesListBinding: FragmentMoviesListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentMoviesListBinding = FragmentMoviesListBinding.bind(view)
        fragmentMoviesListBinding = binding

        initListComponent(binding)
    }

    private fun initListComponent(binding: FragmentMoviesListBinding) {
        binding.rvMovies.adapter = MoviesAdapter(
            MoviesDataSource().getFilms(),
            MoviesAdapter.OnRecyclerItemClicked {
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
                            MovieDetailsFragment.newInstance(it.id),
                            MovieDetailsFragment.MOVIE_DETAILS_FRAGMENT_FLAG
                        )
                        .commit()
                }
            })
    }

    companion object {
        const val MOVIES_LIST_FRAGMENT_FLAG = "moviesListFragment"

        fun newInstance(): MoviesListFragment {
            val fragment = MoviesListFragment()
            return fragment
        }
    }
}
