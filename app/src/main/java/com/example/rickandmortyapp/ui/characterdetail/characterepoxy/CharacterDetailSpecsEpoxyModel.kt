package com.example.rickandmortyapp.ui.characterdetail.characterepoxy

import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.ModelCharacterDetailSpecsBinding
import com.example.rickandmortyapp.utils.ViewBindingEpoxyModel

class CharacterDetailSpecsEpoxyModel(
    val origin: String,
    val species: String
) : ViewBindingEpoxyModel<ModelCharacterDetailSpecsBinding>(
    R.layout.model_character_detail_specs
) {
    override fun ModelCharacterDetailSpecsBinding.bind() {
        originTextView.text = origin
        speciesTextView.text=species
    }
}