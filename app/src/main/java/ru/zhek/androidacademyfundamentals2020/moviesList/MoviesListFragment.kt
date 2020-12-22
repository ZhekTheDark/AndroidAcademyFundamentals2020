package ru.zhek.androidacademyfundamentals2020.moviesList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.databinding.FragmentMoviesListBinding
import ru.zhek.androidacademyfundamentals2020.movieDetails.MovieDetailsFragment

private const val DEFAULT_SPAN = 1

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesListBinding.bind(view)

        initListComponent()
    }

    // TODO
    private fun initListComponent() {
        setSpanSizeLookup()
/*
//        val job = Job()
//        val scope = CoroutineScope(job + Dispatchers.Main)
        var movies: List<Movie> = listOf()
        CoroutineScope(Dispatchers.Default).launch {
//        scope.launch {
            withContext(Dispatchers.Main) {
                delay(1_000)
                movies = withContext(Dispatchers.IO) {
                    loadMovies(context!!)
                }

                delay(1_000)
                Log.d("Movies", movies.first().toString())
                if (binding.rvMovies.adapter is MoviesAdapter)
                    Log.d("Movies", "vse ok")
                binding.rvMovies.adapter?.notifyDataSetChanged()
            }
        }*/

        binding.rvMovies.apply {
            setHasFixedSize(true)
            adapter?.setHasStableIds(true)

            adapter = MoviesAdapter(
//                MoviesDataSource().getFilms(),
                onRecyclerItemClicked()
            )
        }

//        TODO
        (binding.rvMovies.adapter as MoviesAdapter).apply {
            updateData(context!!)
            notifyDataSetChanged()
        }

    }

    private fun setSpanSizeLookup() {
        (binding.rvMovies.layoutManager as GridLayoutManager).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (position) {
                        MoviesAdapter.HEADER_POSITION -> spanCount
                        else -> DEFAULT_SPAN
                    }
                }
            }
        }
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
