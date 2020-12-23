package ru.zhek.androidacademyfundamentals2020.moviesList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.databinding.FragmentMoviesListBinding
import ru.zhek.androidacademyfundamentals2020.movieDetails.MovieDetailsFragment

private const val DEFAULT_SPAN = 1

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    private val job: Job = Job()
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + job)
    private lateinit var jobUpdateData: Job

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesListBinding.bind(view)

        //TODO
        Log.d("TagLifecycle", "onViewCreated")

        initListComponent()
    }

    // TODO
    private fun initListComponent() {
        setSpanSizeLookup()

        binding.rvMovies.apply {
            setHasFixedSize(true)
            adapter?.setHasStableIds(true)

            adapter = MoviesAdapter(onRecyclerItemClicked()).also {
                jobUpdateData = scope.launch {
                    Log.d("MyLog", "coroutine started")
                    it.updateData(requireContext())
                }
            }
        }

//        (binding.rvMovies.adapter as MoviesAdapter).apply {
//            jobUpdateData = scope.launch {
//                updateData(requireContext())
//            }
//        }

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
//        TODO
        Log.d("TagLifecycle", "onDestroyView")
        _binding = null
        jobUpdateData.cancel()
        super.onDestroyView()
    }

    //TODO
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TagLifecycle", "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TagLifecycle", "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.d("TagLifecycle", "onViewStateRestored")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        Log.d("TagLifecycle", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("TagLifecycle", "onResume")
        super.onResume()
    }

    override fun onStop() {
        Log.d("TagLifecycle", "onStop")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("TagLifecycle", "onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        Log.d("TagLifecycle", "onDestroy")
        super.onDestroy()
    }

    companion object {
        const val MOVIES_LIST_FRAGMENT_FLAG = "moviesListFragment"

        fun newInstance(): MoviesListFragment {
            val fragment = MoviesListFragment()
            return fragment
        }
    }
}
