package com.example.rickandmortyapp.ui.characterlist

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapp.data.ApiRepository
import com.example.rickandmortyapp.data.character.CharacterResponse

class CharacterListPagingSource(
    private val apiRepository: ApiRepository,
    var name: String?,
    var gender: String?,
    var status: String?
) : PagingSource<Int, CharacterResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        val pageNumber = params.key ?: 1


        val response = apiRepository.getAllCharacters(name,status,gender,pageNumber)


        if (!response.isSuccessful) {
            return LoadResult.Error(Throwable("Can't retrieve data!"))
        }

        val nextPageNumber = checkNextOrPrevPage(response.body()!!.info.next)
        val prevPageNumber = checkNextOrPrevPage(response.body()!!.info.prev)

        return LoadResult.Page(
            data = response.body()?.results!!,
            prevKey = prevPageNumber,
            nextKey = nextPageNumber
        )
    }

    private fun checkNextOrPrevPage(item:String?): Int? {
        if(item != null) {
            val uri = Uri.parse(item)
            val nextPageQuery = uri.getQueryParameter("page")
            return nextPageQuery?.toInt()
        }else{
            return null
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? {

        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

