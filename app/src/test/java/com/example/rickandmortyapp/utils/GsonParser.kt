package com.example.rickandmortyapp.utils

import com.example.rickandmortyapp.data.character.CharacterResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

val gson: Gson = GsonBuilder()

    .create()


fun parseFileAsResponse(
    fileName: String,
    type: Type
): CharacterResponse {
    return gson.fromJson(gson.getResourceReader(fileName),type)
}

fun parseFileAsCharacterResponse(fileName: String): CharacterResponse {
    return parseFileAsResponse(fileName, typeOf<CharacterResponse>())
}

fun Gson.getResourceReader(fileName: String): BufferedReader {
    val resource = javaClass.classLoader!!.getResourceAsStream(fileName)
    return BufferedReader(InputStreamReader(resource, Charsets.UTF_8))
}