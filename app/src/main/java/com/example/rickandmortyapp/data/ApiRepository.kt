package com.example.rickandmortyapp.data

import com.example.rickandmortyapp.data.remote.RemoteDataSource
import com.example.rickandmortyapp.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource
) {
    fun getCharacterById(characterId :Int) =
        performNetworkOperation {
            remoteDataSource.getCharacterById(characterId)
        }

    fun getAllCharacters(
        characterName: String?,
        characterStatus: String?,
        characterGender: String?,
        characterSpecies: String?,
        characterType: String?
    ) =
        performNetworkOperation {
            remoteDataSource.getAllCharacters(characterName,characterStatus,characterGender,characterSpecies,characterType)
        }
}