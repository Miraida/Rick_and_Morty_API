package com.geek.rick_and_morty_api.data.remote.dto.location

import com.geek.rick_and_morty_api.data.remote.dto.Info

data class LocationsDto(
    val info: Info?,
    val results: List<LocationDto>?
)