package com.example.rickandmortyapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.rickandmortyapp.data.ApiRepository
import com.example.rickandmortyapp.data.character.CharacterResponse
import com.example.rickandmortyapp.data.remote.ApiService
import com.example.rickandmortyapp.data.remote.RemoteDataSource
import com.example.rickandmortyapp.ui.characterdetail.CharacterDetailViewModel
import com.example.rickandmortyapp.utils.CoroutineRule
import com.example.rickandmortyapp.utils.Resource
import com.example.rickandmortyapp.utils.getOrAwaitValue
import com.example.rickandmortyapp.utils.parseFileAsCharacterResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CharacterDetailViewModelUnitTest {

    @get:Rule
    var coroutineTestRule = CoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var apiRepository: ApiRepository
    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    private val data = parseFileAsCharacterResponse("fakeCharacterResponseJson.json")
    private val mockSuccessResponse = Response.success(data)

    private val errorResponse = "{\n\"error\": \"Character not found\"\n }"
    private val errorResponseBody =
        errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
    val mockUnSuccessResponse = Response.error<CharacterResponse>(404, errorResponseBody)


    private var characterStateObserver = mock<Observer<Resource<Any>>>()

    @Before
    fun setUp() {
        remoteDataSource  = RemoteDataSource(apiService)
        apiRepository = ApiRepository(remoteDataSource)
        characterDetailViewModel = CharacterDetailViewModel(apiRepository)
    }

    @Test
    fun `get live data from viewModel and check is success`() = coroutineTestRule.runBlockingTest {

        whenever(apiService.getCharacterById(anyInt())).thenReturn(mockSuccessResponse)



        characterDetailViewModel.getCharacterById(999).observeForever(characterStateObserver)

        characterDetailViewModel.getCharacterById(999).getOrAwaitValue()


        verify(characterStateObserver).onChanged(Resource.loading())
        verify(characterStateObserver).onChanged(Resource.success(data))
    }

    @Test
    fun `get live data from viewModel and check is unsuccessful`() =
        coroutineTestRule.runBlockingTest {

            whenever(apiService.getCharacterById(anyInt())).thenReturn(mockUnSuccessResponse)

            characterDetailViewModel.getCharacterById(1000).observeForever(characterStateObserver)

            characterDetailViewModel.getCharacterById(1000).getOrAwaitValue()

            verify(characterStateObserver).onChanged(Resource.loading())
            verify(characterStateObserver).onChanged(Resource.error("Error: ERROR",null))

        }
}