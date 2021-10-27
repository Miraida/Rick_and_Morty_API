package com.geek.rick_and_morty_api.ui.episode_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.geek.android_trainee_task_2021_mira.common.network.Status
import com.geek.rick_and_morty_api.R
import com.geek.rick_and_morty_api.common.Constants
import com.geek.rick_and_morty_api.common.base.BaseFragment
import com.geek.rick_and_morty_api.databinding.EpisodeDetailFragmentBinding
import com.geek.rick_and_morty_api.domain.model.Characters
import com.geek.rick_and_morty_api.domain.model.Episode
import com.geek.rick_and_morty_api.extension.errorDialog
import com.geek.rick_and_morty_api.extension.mapTo
import com.geek.rick_and_morty_api.extension.visibility
import com.geek.rick_and_morty_api.ui.character.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EpisodeDetailFragment : BaseFragment<EpisodeDetailFragmentBinding>() {

    private val viewModel: EpisodeDetailViewModel by viewModels()
    private var ids = ""
    @Inject
    lateinit var adapter:CharacterAdapter

    override fun bind(): EpisodeDetailFragmentBinding {
        return EpisodeDetailFragmentBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
        viewModel.progressBar.observe(viewLifecycleOwner, {
            ui.progressBar.visibility(it)
        })

        ui.moreCharacters.setOnClickListener {
            getCharacters()
            ui.charactersLayout.visibility(true)
        }
    }

    private fun getCharacters() {
        Log.e("TAG", "getCharacters: $ids" )
        viewModel.fetchCharacters(ids).observe(viewLifecycleOwner,{
            when (it.status) {
                Status.LOADING -> {
                    viewModel.progressBar.value = true
                }
                Status.SUCCESS -> {
                    viewModel.progressBar.value = false
                     setupCharactersData(it.data)
                }
                Status.ERROR -> {
                    viewModel.progressBar.value = false
                    it.msg?.let { it1 -> requireContext().errorDialog(it.code.toString(), it1) }
                }
            }
        })
    }

    private fun setupCharactersData(data: List<Characters>?) {
        if (data!=null)
        {
            ui.rvCharacters.adapter = adapter
            adapter.update(data,this::onItemClick)
        }
    }

    private fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt(Constants.character_id_key,id)
        findNavController().navigate(R.id.navigation_detail_character,bundle)
    }

    private fun setupData(data: Episode?) {
        ui.nameTv.text = data?.name
        ui.airDateTv.text = data?.air_date
        ui.episodeTv.text = data?.episode

        ids = data?.characters?.mapTo().toString()
    }

    override fun setupUI() {
        if (arguments != null) {
            arguments?.getInt(Constants.episode_id_key)?.let { getEpisode(it) }
        }
    }

    private fun getEpisode(id: Int) {
        viewModel.fetchEpisode(id).observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    viewModel.progressBar.value = true
                }
                Status.SUCCESS -> {
                    viewModel.progressBar.value = false
                    setupData(it.data)
                }
                Status.ERROR -> {
                    viewModel.progressBar.value = false
                    it.msg?.let { it1 -> requireContext().errorDialog(it.code.toString(), it1) }
                }
            }
        })
    }



}