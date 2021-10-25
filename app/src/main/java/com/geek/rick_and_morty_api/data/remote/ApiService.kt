package com.geek.rick_and_morty_api.data.remote

import com.geek.android_trainee_task_2021_mira.common.network.Resource
import com.geek.rick_and_morty_api.data.remote.dto.character.CharacterDto
import com.geek.rick_and_morty_api.data.remote.dto.character.CharactersDto
import com.geek.rick_and_morty_api.data.remote.dto.episode.EpisodeDto
import com.geek.rick_and_morty_api.data.remote.dto.episode.EpisodesDto
import com.geek.rick_and_morty_api.data.remote.dto.location.LocationDto
import com.geek.rick_and_morty_api.data.remote.dto.location.LocationsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(): Response<Resource<CharactersDto>>

    @GET("location")
    suspend fun getAllLocations(): Response<Resource<LocationsDto>>

    @GET("episode")
    suspend fun getAllEpisodes(): Response<Resource<EpisodesDto>>

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: String
    ): Response<Resource<CharacterDto>>

    @GET("location/{id}")
    suspend fun getLocation(
        @Path("id") id: String
    ): Response<Resource<LocationDto>>

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: String
    ): Response<Resource<EpisodeDto>>
}