package ru.zhek.androidacademyfundamentals2020.moviesList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.data.MoviesDataSource
import ru.zhek.androidacademyfundamentals2020.databinding.FragmentMoviesListBinding
import ru.zhek.androidacademyfundamentals2020.movieDetails.MovieDetailsFragment

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesListBinding.bind(view)

        initListComponent()
    }

    private fun initListComponent() {
        binding.rvMovies.adapter = MoviesAdapter(
            MoviesDataSource().getFilms(),
            onRecyclerItemClicked()
        )
    }

    private fun onRecyclerItemClicked(): MoviesAdapter.OnRecyclerItemClicked {
        return MoviesAdapter.OnRecyclerItemClicked {
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
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val MOVIES_LIST_FRAGMENT_FLAG = "moviesListFragment"

        fun newInstance(): MoviesListFragment {
            val fragment = MoviesListFragment()
            return fragment
        }
    }
}
