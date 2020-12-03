package ru.zhek.androidacademyfundamentals2020.data.models

data class MovieDetails(
    val name: String,
    val backposter: Int,
    val pg: Int,
    val genres: String,
    val rating: Int,
    val reviews: Int,
    val storyline: String,
    val actors: List<Actor>
)