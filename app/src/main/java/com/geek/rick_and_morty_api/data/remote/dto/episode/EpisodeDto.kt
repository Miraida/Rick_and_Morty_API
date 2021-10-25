package com.geek.rick_and_morty_api.data.remote.dto.episode

data class EpisodeDto(
    val air_date: String?,
    val characters: List<String>?,
    val created: String?,
    val episode: String?,
    val id: Int?,
    val name: String?,
    val url: String?
)