package com.geek.rick_and_morty_api.ui.character

import com.geek.rick_and_morty_api.common.base.BaseFragment
import com.geek.rick_and_morty_api.databinding.FragmentCharacterBinding

class CharacterFragment : BaseFragment<FragmentCharacterBinding>() {

    override fun bind(): FragmentCharacterBinding {
        return FragmentCharacterBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
    }

    override fun setupUI() {
    }


}