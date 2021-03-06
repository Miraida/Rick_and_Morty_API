package com.geek.rick_and_morty_api.ui.location_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geek.android_trainee_task_2021_mira.common.network.Resource
import com.geek.rick_and_morty_api.data.repository.RemoteRepositoryImpl
import com.geek.rick_and_morty_api.domain.model.Characters
import com.geek.rick_and_morty_api.domain.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(private val repo: RemoteRepositoryImpl) :
    ViewModel() {
    val progressBar = MutableLiveData<Boolean>()
    fun fetchLocation(id: Int): LiveData<Resource<Location>> = repo.getLocation(id)

    fun fetchCharacters(ids: String): LiveData<Resource<List<Characters>>> {
        return repo.getCharactersById(ids)
    }
}