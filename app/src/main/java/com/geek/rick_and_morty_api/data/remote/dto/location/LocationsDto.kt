package com.geek.rick_and_morty_api.data.remote.dto.location

import com.geek.rick_and_morty_api.data.remote.dto.Info
import com.geek.rick_and_morty_api.domain.model.Locations

data class LocationsDto(
    val info: Info?,
    val results: List<LocationDto>?
)

fun LocationsDto.mapTo(): List<Locations> {
    val list = ArrayList<Locations>()

    for (i in this.results!!) {
        list.add(Locations(i.name, i.type,i.id))
    }
    return list
}