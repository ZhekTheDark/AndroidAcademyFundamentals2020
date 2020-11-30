package ru.zhek.androidacademyfundamentals2020

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class MoviesListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ConstraintLayout>(R.id.avengers)
            .setOnClickListener {
                activity?.supportFragmentManager?.apply {
                    beginTransaction().addToBackStack(null).replace(
                        R.id.fragments_container,
                        MovieDetailsFragment.newInstance(),
                        MainActivity.MOVIE_DETAILS_FRAGMENT_FLAG
                    )
                        .commit()
                }
            }
    }

    companion object {
        fun newInstance(): MoviesListFragment {
            val fragment = MoviesListFragment()
            return fragment
        }
    }
}
