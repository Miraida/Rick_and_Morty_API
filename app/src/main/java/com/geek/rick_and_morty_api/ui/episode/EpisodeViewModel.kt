package com.geek.rick_and_morty_api.ui.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geek.android_trainee_task_2021_mira.common.network.Resource
import com.geek.rick_and_morty_api.data.repository.RemoteRepositoryImpl
import com.geek.rick_and_morty_api.domain.model.Episodes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(private val repo: RemoteRepositoryImpl) : ViewModel() {

    fun fetchLocations(): LiveData<Resource<List<Episodes>>> = repo.getEpisodes()

}