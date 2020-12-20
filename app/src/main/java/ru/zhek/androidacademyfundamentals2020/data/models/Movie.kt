package ru.zhek.androidacademyfundamentals2020.data.models

data class Movie(
    val id: Int,
    val name: String,
    val kinoposter: Int,
    val liked: Boolean,
    val pg: Int,
    val genres: String,
    val rating: Int,
    val reviews: Int,
    val duration: Int,

    val backposter: Int,
    val storyline: String,
    val castList: List<Actor>
)
