package com.geek.rick_and_morty_api.data.remote

import com.geek.rick_and_morty_api.data.remote.dto.character.CharacterDto
import com.geek.rick_and_morty_api.data.remote.dto.character.CharactersDto
import com.geek.rick_and_morty_api.data.remote.dto.episode.EpisodeDto
import com.geek.rick_and_morty_api.data.remote.dto.episode.EpisodesDto
import com.geek.rick_and_morty_api.data.remote.dto.location.LocationDto
import com.geek.rick_and_morty_api.data.remote.dto.location.LocationsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(): Response<CharactersDto>

    @GET("character/")
    suspend fun getCharactersByName(
        @Query("name") name: String,
        @Query("status") status: String,
        @Query("gender") gender: String
    ): Response<CharactersDto>

    @GET("location")
    suspend fun getAllLocations(): Response<LocationsDto>

    @GET("episode")
    suspend fun getAllEpisodes(): Response<EpisodesDto>

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: String
    ): Response<CharacterDto>

    @GET("character/{ids}")
    suspend fun getCharactersById(
        @Path("ids") ids: String
    ): Response<List<CharacterDto>>

    @GET("location/{id}")
    suspend fun getLocation(
        @Path("id") id: String
    ): Response<LocationDto>

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: String
    ): Response<EpisodeDto>
}