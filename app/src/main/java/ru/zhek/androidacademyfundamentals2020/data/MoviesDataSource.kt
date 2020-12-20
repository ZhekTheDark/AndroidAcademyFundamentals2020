package ru.zhek.androidacademyfundamentals2020.data

import ru.zhek.androidacademyfundamentals2020.R
import ru.zhek.androidacademyfundamentals2020.data.models.Actor
import ru.zhek.androidacademyfundamentals2020.data.models.Movie

class MoviesDataSource {
    fun getFilms(): List<Movie> {
        return listOf(
            Movie(
                1,
                "Avengers: End Game",
                R.drawable.avengers_end_game_kinoposter,
                false,
                13,
                "Action, Adventure, Fantasy",
                4,
                125,
                137,
                R.drawable.mask,
                "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thano\\'s actions and restore balance to the universe.",
                listOf(
                    Actor(
                        "Robert Downey Jr.",
                        R.drawable.robert_downey_jr
                    ),
                    Actor(
                        "Chris Evans",
                        R.drawable.chris_evans
                    ),
                    Actor(
                        "Mark Ruffalo",
                        R.drawable.mark_ruffalo
                    ),
                    Actor(
                        "Chris Hemsworth",
                        R.drawable.chris_hemsworth
                    ), Actor(
                        "Scarlett Johansson",
                        R.drawable.scarlett_johansson
                    )
                )
            ),
            Movie(
                2,
                "Tenet",
                R.drawable.tenet_kinoposter,
                true,
                16,
                "Action, Sci-Fi, Thriller",
                5,
                98,
                97,
                R.drawable.tenet_kinoposter,
                "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                listOf(
                    Actor(
                        "Robert Downey Jr.",
                        R.drawable.robert_downey_jr
                    ),
                    Actor(
                        "Chris Evans",
                        R.drawable.chris_evans
                    ),
                    Actor(
                        "Mark Ruffalo",
                        R.drawable.mark_ruffalo
                    ),
                    Actor(
                        "Chris Hemsworth",
                        R.drawable.chris_hemsworth
                    ), Actor(
                        "Scarlett Johansson",
                        R.drawable.scarlett_johansson
                    )
                )
            ),
            Movie(
                3,
                "Black Widow",
                R.drawable.black_widow_kinoposter,
                false,
                13,
                "Action, Adventure, Sci-Fi",
                4,
                1,
                102,
                R.drawable.black_widow_kinoposter,
                "A film about Natasha Romanoff in her quests between the films Civil War and Infinity War.",
                listOf(
                    Actor(
                        "Robert Downey Jr.",
                        R.drawable.robert_downey_jr
                    ),
                    Actor(
                        "Chris Evans",
                        R.drawable.chris_evans
                    ),
                    Actor(
                        "Mark Ruffalo",
                        R.drawable.mark_ruffalo
                    ),
                    Actor(
                        "Chris Hemsworth",
                        R.drawable.chris_hemsworth
                    ), Actor(
                        "Scarlett Johansson",
                        R.drawable.scarlett_johansson
                    )
                )
            ),
            Movie(
                4,
                "Wonder Woman 1984",
                R.drawable.wonder_woman_1984_kinoposter,
                false,
                13,
                "Action, Adventure, Fantasy",
                5,
                74,
                120,
                R.drawable.wonder_woman_1984_kinoposter,
                "Fast forward to the 1980s as Wonder Woman's next big screen adventure finds her facing two all-new foes: Max Lord and The Cheetah.",
                listOf(
                    Actor(
                        "Robert Downey Jr.",
                        R.drawable.robert_downey_jr
                    ),
                    Actor(
                        "Chris Evans",
                        R.drawable.chris_evans
                    ),
                    Actor(
                        "Mark Ruffalo",
                        R.drawable.mark_ruffalo
                    ),
                    Actor(
                        "Chris Hemsworth",
                        R.drawable.chris_hemsworth
                    ), Actor(
                        "Scarlett Johansson",
                        R.drawable.scarlett_johansson
                    )
                )
            ),
            Movie(
                5,
                "Fight Club",
                R.drawable.fight_club_kinoposter,
                true,
                18,
                "Drama",
                5,
                3824,
                139,
                R.drawable.fight_club_kinoposter,
                "An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into something much, much more.",
                listOf(
                    Actor(
                        "Robert Downey Jr.",
                        R.drawable.robert_downey_jr
                    ),
                    Actor(
                        "Chris Evans",
                        R.drawable.chris_evans
                    ),
                    Actor(
                        "Mark Ruffalo",
                        R.drawable.mark_ruffalo
                    ),
                    Actor(
                        "Chris Hemsworth",
                        R.drawable.chris_hemsworth
                    ), Actor(
                        "Scarlett Johansson",
                        R.drawable.scarlett_johansson
                    )
                )
            ),
            Movie(
                6,
                "Pulp Fiction",
                R.drawable.pulp_fiction_kinoposter,
                true,
                18,
                "Crime, Drama",
                5,
                3106,
                154,
                R.drawable.pulp_fiction_kinoposter,
                "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
                listOf(
                    Actor(
                        "Robert Downey Jr.",
                        R.drawable.robert_downey_jr
                    ),
                    Actor(
                        "Chris Evans",
                        R.drawable.chris_evans
                    ),
                    Actor(
                        "Mark Ruffalo",
                        R.drawable.mark_ruffalo
                    ),
                    Actor(
                        "Chris Hemsworth",
                        R.drawable.chris_hemsworth
                    ), Actor(
                        "Scarlett Johansson",
                        R.drawable.scarlett_johansson
                    )
                )
            )
        )
    }
}
