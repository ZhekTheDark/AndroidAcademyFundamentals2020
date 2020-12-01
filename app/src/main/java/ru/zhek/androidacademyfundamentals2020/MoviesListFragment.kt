package ru.zhek.androidacademyfundamentals2020

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ConstraintLayout>(R.id.avengers)
            .setOnClickListener {
                activity?.supportFragmentManager?.apply {
                    beginTransaction()
                        .addToBackStack(null)
                        .replace(
                            R.id.fragments_container,
                            MovieDetailsFragment.newInstance(),
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
