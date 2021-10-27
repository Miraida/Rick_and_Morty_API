package com.geek.rick_and_morty_api.data.remote.dto.location

import com.geek.rick_and_morty_api.domain.model.Location

data class LocationDto(
    val created: String?,
    val dimension: String?,
    val id: Int?,
    val name: String?,
    val residents: List<String>?,
    val type: String?,
    val url: String?
)

fun LocationDto.mapTo(): Location {
    return Location(
        this.created,
        this.dimension,
        this.id,
        this.name,
        this.residents,
        this.type,
        this.url
    )
}
