package com.example.rickandmortyapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickandmortyapp.data.ApiRepository
import com.example.rickandmortyapp.data.remote.ApiService
import com.example.rickandmortyapp.data.remote.RemoteDataSource
import com.example.rickandmortyapp.ui.characterdetail.CharacterDetailViewModel
import com.example.rickandmortyapp.utils.CoroutineRule
import com.example.rickandmortyapp.utils.Resource
import com.example.rickandmortyapp.utils.getOrAwaitValue
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    @get:Rule
    var coroutineTestRule = CoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService :ApiService

    @InjectMocks
    private lateinit var remoteDataSource :RemoteDataSource

    private lateinit var apiRepository : ApiRepository
    private lateinit var characterDetailViewModel :CharacterDetailViewModel

    @Before
    fun setup(){
        //remoteDataSource = RemoteDataSource(apiService)
        apiRepository = ApiRepository(remoteDataSource)
        characterDetailViewModel = CharacterDetailViewModel(apiRepository)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testDeneme() {
        coroutineTestRule.runBlockingTest {
            assertEquals(Resource.Status.LOADING,characterDetailViewModel.getCharacterById(1).getOrAwaitValue().status)
        }
    }
}