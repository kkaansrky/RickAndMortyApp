package com.example.rickandmortyapp.data.remote

import com.example.rickandmortyapp.data.character.CharacterResponse
import com.example.rickandmortyapp.data.characterlist.CharacterListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId: Int
    ): Response<CharacterResponse>


    @GET("character")
    suspend fun getAllCharacters(
        @Query("name") characterName: String?,
        @Query("status") characterStatus: String?,
        @Query("gender") characterGender: String?,
        @Query("page") page: Int?
    ): Response<CharacterListResponse>
}