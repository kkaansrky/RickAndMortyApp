package com.example.rickandmortyapp.ui.characterlist

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.example.rickandmortyapp.data.character.CharacterResponse
import com.example.rickandmortyapp.ui.characterlist.characterlistepoxy.CharacterListCharacterEpoxyModel

class CharacterListEpoxyController() : PagingDataEpoxyController<CharacterResponse>() {

    private var listener: IOnClickCharacter? = null

    override fun buildItemModel(currentPosition: Int, item: CharacterResponse?): EpoxyModel<*> {
        return CharacterListCharacterEpoxyModel(
            item!!.name,
            item.id,
            item.status,
            item.gender,
            item.image
        ).also {
            it.id(item.id)
            it.onCharacterClicked = listener!!
        }
    }

    fun setListener(mListener: IOnClickCharacter) {
        listener = mListener
    }
}

