package com.example.rickandmortyapp.data.characterlist

import com.example.rickandmortyapp.data.character.CharacterResponse

data class CharacterListResponse(
    val info: Info,
    val results: List<CharacterResponse>
)

data class Info(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)