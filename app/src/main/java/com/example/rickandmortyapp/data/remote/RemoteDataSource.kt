package com.example.rickandmortyapp.data.remote

import com.example.rickandmortyapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getCharacterById(characterId: Int) = getResult {
        apiService.getCharacterById(characterId)
    }

    suspend fun getAllCharacters(
        characterName: String?,
        characterStatus: String?,
        characterGender: String?,
        page: Int?
    ) = apiService.getAllCharacters(
        characterName,
        characterStatus,
        characterGender,
        page
    )
}