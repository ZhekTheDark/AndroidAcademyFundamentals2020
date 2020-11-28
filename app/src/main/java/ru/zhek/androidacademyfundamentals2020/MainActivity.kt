package ru.zhek.androidacademyfundamentals2020

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.zhek.androidacademyfundamentals2020.MoviesListFragment.*
import ru.zhek.androidacademyfundamentals2020.MovieDetailsFragment.*

class MainActivity : AppCompatActivity(), MoviesListFragmentClickListener,
    MovieDetailsFragmentClickListener {

    private var moviesListFragment: MoviesListFragment? = null
    private var movieDetailsFragment: MovieDetailsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            moviesListFragment = MoviesListFragment()
            moviesListFragment?.apply {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragments_container, this, MOVIES_LIST_FRAGMENT_FLAG)
                    .commit()
            }
        } else {
            moviesListFragment = supportFragmentManager
                .findFragmentByTag(MOVIES_LIST_FRAGMENT_FLAG) as? MoviesListFragment

            movieDetailsFragment = supportFragmentManager
                .findFragmentByTag(MOVIE_DETAILS_FRAGMENT_FLAG) as? MovieDetailsFragment
        }
    }

    override fun moveToMovieDetailsFragment() {
        movieDetailsFragment = MovieDetailsFragment()
        movieDetailsFragment?.apply {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .add(R.id.fragments_container, this, MOVIE_DETAILS_FRAGMENT_FLAG)
                .commit()
        }
    }

    override fun moveToMoviesListFragment() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        }
    }

    companion object {
        const val MOVIES_LIST_FRAGMENT_FLAG = "moviesListFragment"
        const val MOVIE_DETAILS_FRAGMENT_FLAG = "movieDetailsFragment"
    }
}