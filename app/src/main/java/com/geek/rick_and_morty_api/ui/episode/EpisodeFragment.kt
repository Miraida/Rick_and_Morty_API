package com.geek.rick_and_morty_api.ui.episode

import com.geek.rick_and_morty_api.common.base.BaseFragment
import com.geek.rick_and_morty_api.databinding.FragmentEpisodeBinding

class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>() {

    override fun bind(): FragmentEpisodeBinding {
        return FragmentEpisodeBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
    }

    override fun setupUI() {
    }
}