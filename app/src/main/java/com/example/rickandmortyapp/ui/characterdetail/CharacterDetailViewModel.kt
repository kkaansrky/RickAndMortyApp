package com.example.rickandmortyapp.ui.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.data.ApiRepository
import com.example.rickandmortyapp.data.character.CharacterResponse
import com.example.rickandmortyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getCharacterById(characterId :Int): LiveData<Resource<CharacterResponse>> {
        return apiRepository.getCharacterById(characterId)
    }
}