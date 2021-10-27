package com.geek.rick_and_morty_api.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geek.android_trainee_task_2021_mira.common.network.Resource
import com.geek.rick_and_morty_api.data.repository.RemoteRepositoryImpl
import com.geek.rick_and_morty_api.domain.model.Locations
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val repo: RemoteRepositoryImpl) : ViewModel() {
    val progressBar = MutableLiveData<Boolean>()
    fun fetchLocations(): LiveData<Resource<List<Locations>>> = repo.getLocations()
}