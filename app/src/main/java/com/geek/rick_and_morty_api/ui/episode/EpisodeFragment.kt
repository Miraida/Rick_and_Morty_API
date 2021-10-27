package com.geek.rick_and_morty_api.ui.episode

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.geek.android_trainee_task_2021_mira.common.network.Status
import com.geek.rick_and_morty_api.R
import com.geek.rick_and_morty_api.common.Constants
import com.geek.rick_and_morty_api.common.base.BaseFragment
import com.geek.rick_and_morty_api.databinding.FragmentEpisodeBinding
import com.geek.rick_and_morty_api.domain.model.Episodes
import com.geek.rick_and_morty_api.extension.errorDialog
import com.geek.rick_and_morty_api.extension.visibility
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>() {

    private val viewModel: EpisodeViewModel by viewModels()
    @Inject
    lateinit var adapter: EpisodeAdapter

    private fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt(Constants.episode_id_key, id)
        findNavController().navigate(R.id.navigation_detail_episode, bundle)
    }


    override fun bind(): FragmentEpisodeBinding {
        return FragmentEpisodeBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {

        viewModel.progressBar.observe(viewLifecycleOwner, {
            ui.progressBar.visibility(it)
        })

        viewModel.fetchEpisodes().observe(viewLifecycleOwner, {
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

    private fun setupData(data: List<Episodes>?) {
        if (data!=null) adapter.update(data,this::onItemClick)
    }

    override fun setupUI() {
        ui.episodeRv.adapter = adapter
    }


}