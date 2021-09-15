package com.example.rickandmortyapp.ui.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.data.ApiRepository
import com.example.rickandmortyapp.data.characterlist.CharacterListResponse
import com.example.rickandmortyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getAllCharacters(
        characterName: String?,
        characterStatus: String?,
        characterGender: String?,
        characterSpecies: String?,
        characterType: String?
    ): LiveData<Resource<CharacterListResponse>> {
        return apiRepository.getAllCharacters(characterName,characterStatus,characterGender,characterSpecies,characterType)
    }
}