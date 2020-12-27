package ru.zhek.androidacademyfundamentals2020.moviesList

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.*
import ru.zhek.androidacademyfundamentals2020.NetworkChecker
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.data.Movie
import ru.zhek.androidacademyfundamentals2020.data.loadMovies
import ru.zhek.androidacademyfundamentals2020.databinding.FragmentMoviesListBinding
import ru.zhek.androidacademyfundamentals2020.movieDetails.MovieDetailsFragment

private const val DEFAULT_SPAN = 1

class MoviesListFragment : Fragment(R.layout.fragment_movies_list), NetworkChecker.Listener {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("CoroutineExceptionHandler got $exception in $coroutineContext")
    }
    private val job = Job()
    private val scope: CoroutineScope = CoroutineScope(
        Dispatchers.Main + job + exceptionHandler
    )
    private lateinit var jobUpdateData: Job

    private var movies: List<Movie> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesListBinding.bind(view)

        NetworkChecker.addSubscriber(this)

        binding.rvMovies.apply {
            setHasFixedSize(true)
            adapter?.setHasStableIds(true)
        }
        drawUI()
    }

    private fun initListComponent() {
        setSpanSizeLookup()

        binding.rvMovies.apply {

            jobUpdateData = scope.launch {
                fetchData(requireContext())
                adapter = MoviesAdapter(movies, onRecyclerItemClicked())
            }
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

    private suspend fun fetchData(context: Context) {
        movies = loadMovies(context)
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
        jobUpdateData.cancel()
        scope.cancel()
        NetworkChecker.removeSubscriber(this)
        super.onDestroyView()
    }

    override fun onStateChanged() {
        activity?.runOnUiThread {
            drawUI()
        }
        super.onStateChanged()
    }

    private fun drawUI() {
        if (NetworkChecker.isConnected) {
            binding.ivError.visibility = View.GONE
            initListComponent()
        } else {
            binding.ivError.visibility = View.VISIBLE
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
