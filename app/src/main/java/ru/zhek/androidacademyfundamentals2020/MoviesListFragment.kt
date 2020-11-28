package ru.zhek.androidacademyfundamentals2020

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class MoviesListFragment : Fragment() {

    private var listener: MoviesListFragmentClickListener? = null

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
            .setOnClickListener { listener?.moveToMovieDetailsFragment() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MoviesListFragmentClickListener) listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface MoviesListFragmentClickListener {
        fun moveToMovieDetailsFragment()
    }
}