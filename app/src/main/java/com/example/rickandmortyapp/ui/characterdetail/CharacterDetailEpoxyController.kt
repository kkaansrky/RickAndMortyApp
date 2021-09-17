package com.example.rickandmortyapp.ui.characterdetail

import com.airbnb.epoxy.EpoxyController
import com.example.rickandmortyapp.data.character.CharacterResponse
import com.example.rickandmortyapp.ui.characterdetail.characterepoxy.CharacterDetailHeaderEpoxyModel
import com.example.rickandmortyapp.ui.characterdetail.characterepoxy.CharacterDetailImageEpoxyModel
import com.example.rickandmortyapp.ui.characterdetail.characterepoxy.CharacterDetailSpecsEpoxyModel

class CharacterDetailEpoxyController : EpoxyController() {

    var character: CharacterResponse? = null
        set(value) {
            field = value
            if (field != null) {
                requestModelBuild()
            }
        }

    override fun buildModels() {
        CharacterDetailImageEpoxyModel(
            character!!.image
        ).id("image").addTo(this)

        // Header Model
        CharacterDetailHeaderEpoxyModel(
            character!!.name,
            character!!.gender,
            character!!.status
        ).id("header").addTo(this)

        CharacterDetailSpecsEpoxyModel(
            character!!.origin.name,
            character!!.species
        ).id("specs").addTo(this)

    }
}