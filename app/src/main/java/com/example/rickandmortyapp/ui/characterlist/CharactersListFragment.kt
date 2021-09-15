package com.example.rickandmortyapp.ui.characterlist

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmortyapp.data.character.CharacterResponse
import com.example.rickandmortyapp.databinding.FragmentCharactersListBinding
import com.example.rickandmortyapp.ui.characterfilter.CharacterFilterFragment
import com.example.rickandmortyapp.ui.characterfilter.ICharacterFilter
import com.example.rickandmortyapp.utils.Resource
import com.example.rickandmortyapp.utils.hide
import com.example.rickandmortyapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersListFragment : Fragment() {
    private var _binding: FragmentCharactersListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CharacterListViewModel by viewModels()
    private var characterListAdapter: CharacterListAdapter = CharacterListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        val view = binding.root
        initView()
        return view
    }

    private fun initView() {
        getCharactersList(null,null,null,null,null)
        setRecyclerView()
        getFilterValuesSet()
    }

    private fun getFilterValuesSet() {
        val callback = object : ICharacterFilter {
            override fun filterValue(name: String?, status: String?, gender: String?) {
                getCharactersList(name,status,gender,null,null)
            }
        }
        setFilterButton(callback)
    }

    private fun setFilterButton(callback: ICharacterFilter) {
        binding.filterButton.setOnClickListener {
            val dialog = CharacterFilterFragment(callback)
            dialog.show(requireActivity().supportFragmentManager,"Filter List")
        }
    }

    private fun setRecyclerView() {
        binding.apply {
            characterListRecycler.layoutManager =
                GridLayoutManager(context,2)

            binding.characterListRecycler.adapter = characterListAdapter
        }
    }

    fun getCharactersList(
        characterName: String?,
        characterStatus: String?,
        characterGender: String?,
        characterSpecies: String?,
        characterType: String?
    ) {
        viewModel.getAllCharacters(characterName,characterStatus,characterGender,characterSpecies,characterType).observe(viewLifecycleOwner,{
            Log.d(TAG, "getCharactersList: "+it.message)
            when(it.status){
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    it.data?.results?.let { it -> updateRecyclerViewList(it) }
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.hide()
                }
            }
        })
    }

    private fun updateRecyclerViewList(characters: List<CharacterResponse>) {
        characterListAdapter.setCharactersList(characters as ArrayList<CharacterResponse>)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
