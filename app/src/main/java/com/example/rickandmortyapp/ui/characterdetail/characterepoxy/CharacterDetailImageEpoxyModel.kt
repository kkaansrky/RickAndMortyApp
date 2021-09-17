package com.example.rickandmortyapp.ui.characterdetail.characterepoxy

import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.ModelCharacterDetailImageBinding
import com.example.rickandmortyapp.utils.ViewBindingEpoxyModel

@EpoxyModelClass
class CharacterDetailImageEpoxyModel(
    val imageUrl: String
) : ViewBindingEpoxyModel<ModelCharacterDetailImageBinding>(R.layout.model_character_detail_image) {

    override fun ModelCharacterDetailImageBinding.bind() {
        Glide
            .with(headerImageView.context)
            .load(imageUrl)
            .into(headerImageView)
    }
}