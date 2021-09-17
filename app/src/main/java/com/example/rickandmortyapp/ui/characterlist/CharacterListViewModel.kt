package com.example.rickandmortyapp.ui.characterlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortyapp.data.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun filterCharacters(name: String?,gender:String?,status:String?) = Pager(
        PagingConfig(20, 40, enablePlaceholders = false)
    ) {
        CharacterListPagingSource(apiRepository,name,gender,status)
    }.flow
        .cachedIn(viewModelScope)
}