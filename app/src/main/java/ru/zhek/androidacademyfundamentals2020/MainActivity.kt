package ru.zhek.androidacademyfundamentals2020

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.zhek.androidacademyfundamentals2020.moviesList.MoviesListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragments_container,
                    MoviesListFragment.newInstance(),
                    MoviesListFragment.MOVIES_LIST_FRAGMENT_FLAG
                )
                .commit()
        }

        NetworkChecker.start(this)
    }
}
