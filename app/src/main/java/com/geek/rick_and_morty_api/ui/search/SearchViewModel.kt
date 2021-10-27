package com.geek.rick_and_morty_api.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geek.android_trainee_task_2021_mira.common.network.Resource
import com.geek.rick_and_morty_api.data.repository.RemoteRepositoryImpl
import com.geek.rick_and_morty_api.domain.model.Characters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo: RemoteRepositoryImpl) : ViewModel() {

    val progressBar = MutableLiveData<Boolean>()

    fun fetchCharactersByName(
        name: String,
        status: String,
        gender: String
    ): LiveData<Resource<List<Characters>>> {
        return repo.getCharactersByName(name, status, gender)
    }

    val name = MutableLiveData<String>()
    val status = MutableLiveData<String>()
    val gender = MutableLiveData<String>()
}