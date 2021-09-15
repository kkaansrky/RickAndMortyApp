package com.example.rickandmortyapp.ui.characterdetail.characterepoxy

import com.airbnb.epoxy.EpoxyModelClass
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.ModelCharacterDetailHeaderBinding
import com.example.rickandmortyapp.utils.ViewBindingEpoxyModel

@EpoxyModelClass
class CharacterDetailHeaderEpoxyModel(
    val name: String,
    val gender: String,
    val status: String
) : ViewBindingEpoxyModel<ModelCharacterDetailHeaderBinding>(
    R.layout.model_character_detail_header) {
    override fun ModelCharacterDetailHeaderBinding.bind() {
        nameTextView.text = name
        aliveTextView.text = status

        when (gender) {
            "Male" -> genderImageView.setImageResource(R.drawable.ic_male)
            "Female" -> genderImageView.setImageResource(R.drawable.ic_female)
            else -> genderImageView.setImageResource(R.drawable.ic_genderless)
        }

        when (status) {
            "Alive" -> aliveImageView.setImageResource(R.drawable.ic_alive)
            "Dead" -> aliveImageView.setImageResource(R.drawable.ic_dead)
            else -> aliveImageView.setImageResource(R.drawable.ic_unknown)
        }
    }
}