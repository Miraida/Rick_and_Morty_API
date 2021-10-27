package com.geek.rick_and_morty_api.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geek.android_trainee_task_2021_mira.common.network.Resource
import com.geek.rick_and_morty_api.data.remote.ApiService
import com.geek.rick_and_morty_api.data.remote.dto.character.mapTo
import com.geek.rick_and_morty_api.data.remote.dto.episode.mapTo
import com.geek.rick_and_morty_api.data.remote.dto.location.mapTo
import com.geek.rick_and_morty_api.domain.model.*
import com.geek.rick_and_morty_api.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val api: ApiService) : RemoteRepository {

    override suspend fun getCharacters(): LiveData<Resource<List<Characters>>> = liveData {
        emit(Resource.loading(null))

        val response = api.getAllCharacters()

        emit(
            if (response.isSuccessful && response.body() != null) {
                Resource.success(response.body()?.data?.mapTo(), response.code())
            } else {
                Resource.error(response.message(), null, response.code())
            }
        )

    }

    override suspend fun getCharacter(id: Int): LiveData<Resource<Character>> = liveData {
        emit(Resource.loading(null))

        val response = api.getCharacter(id.toString())

        emit(
            if (response.isSuccessful && response.body() != null) {
                Resource.success(response.body()?.data?.mapTo(), response.code())
            } else {
                Resource.error(response.message(), null, response.code())
            }
        )

    }

    override suspend fun getEpisodes(): LiveData<Resource<List<Episodes>>> = liveData {
        emit(Resource.loading(null))

        val response = api.getAllEpisodes()

        emit(
            if (response.isSuccessful && response.body() != null) {
                Resource.success(response.body()?.data?.mapTo(), response.code())
            } else {
                Resource.error(response.message(), null, response.code())
            }
        )

    }

    override suspend fun getEpisode(id: Int): LiveData<Resource<Episode>> = liveData {
        emit(Resource.loading(null))

        val response = api.getEpisode(id.toString())

        emit(
            if (response.isSuccessful && response.body() != null) {
                Resource.success(response.body()?.data?.mapTo(), response.code())
            } else {
                Resource.error(response.message(), null, response.code())
            }
        )

    }

    override suspend fun getLocations(): LiveData<Resource<List<Locations>>> = liveData {
        emit(Resource.loading(null))

        val response = api.getAllLocations()

        emit(
            if (response.isSuccessful && response.body() != null) {
                Resource.success(response.body()?.data?.mapTo(), response.code())
            } else {
                Resource.error(response.message(), null, response.code())
            }
        )

    }

    override suspend fun getLocation(id: Int): LiveData<Resource<Location>> = liveData {
        emit(Resource.loading(null))

        val response = api.getLocation(id.toString())

        emit(
            if (response.isSuccessful && response.body() != null) {
                Resource.success(response.body()?.data?.mapTo(), response.code())
            } else {
                Resource.error(response.message(), null, response.code())
            }
        )

    }

}