package ru.zhek.androidacademyfundamentals2020

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.textView_back)
            .setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
            }
    }

    companion object {
        const val MOVIE_DETAILS_FRAGMENT_FLAG = "movieDetailsFragment"

        fun newInstance(): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            return fragment
        }
    }
}