package ru.zhek.androidacademyfundamentals2020

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragments_container,
                    MoviesListFragment.newInstance(),
                    MOVIES_LIST_FRAGMENT_FLAG
                )
                .commit()
        }
    }

    companion object {
        const val MOVIES_LIST_FRAGMENT_FLAG = "moviesListFragment"
        const val MOVIE_DETAILS_FRAGMENT_FLAG = "movieDetailsFragment"
    }
}
