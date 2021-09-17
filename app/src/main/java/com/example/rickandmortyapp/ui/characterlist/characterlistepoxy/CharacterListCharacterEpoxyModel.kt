package com.example.rickandmortyapp.ui.characterlist.characterlistepoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.ItemCharacterListBinding
import com.example.rickandmortyapp.ui.characterlist.IOnClickCharacter
import com.example.rickandmortyapp.utils.ViewBindingEpoxyModel

@EpoxyModelClass
class CharacterListCharacterEpoxyModel(
    val name: String,
    val id: Int,
    val status: String,
    val gender: String,
    val imageUrl: String
) : ViewBindingEpoxyModel<ItemCharacterListBinding>(R.layout.item_character_list) {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onCharacterClicked: IOnClickCharacter

    override fun ItemCharacterListBinding.bind() {

        root.setOnClickListener {
            onCharacterClicked.onCharacterClicked(id)
        }

        Glide
            .with(characterImageView.context)
            .load(imageUrl)
            .into(characterImageView)

        characterNameTextView.text = name

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