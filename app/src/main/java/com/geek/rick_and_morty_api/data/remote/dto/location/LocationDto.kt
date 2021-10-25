package com.geek.rick_and_morty_api.data.remote.dto.location

data class LocationDto(
    val created: String?,
    val dimension: String?,
    val id: Int?,
    val name: String?,
    val residents: List<String>?,
    val type: String?,
    val url: String?
)