package com.example.rickandmortyapp.ui.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyapp.databinding.FragmentCharactersListBinding
import com.example.rickandmortyapp.ui.characterfilter.CharacterFilterFragment
import com.example.rickandmortyapp.ui.characterfilter.ICharacterFilter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersListFragment : Fragment() , IOnClickCharacter {
    private var _binding: FragmentCharactersListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CharacterListViewModel by viewModels()

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
        getCharactersList(null, null, null)
        getFilterValuesSet()
    }

    private fun getFilterValuesSet() {
        val callback = object : ICharacterFilter {
            override fun filterValue(name: String?, status: String?, gender: String?) {
                getCharactersList(name, status, gender)
            }
        }
        setFilterButton(callback)
    }

    private fun setFilterButton(callback: ICharacterFilter) {
        binding.filterButton.setOnClickListener {
            val dialog = CharacterFilterFragment(callback)
            dialog.show(requireActivity().supportFragmentManager, "Filter List")
        }
    }

    fun getCharactersList(
        characterName: String?,
        characterStatus: String?,
        characterGender: String?,
    ) {

        val epoxyController = CharacterListEpoxyController()
        epoxyController.setListener(this)

        lifecycleScope.launch {
            viewModel.filterCharacters(characterName,characterGender,characterStatus).collectLatest {
                epoxyController.submitData(it)
            }
        }
        binding.characterListRecycler.setController(epoxyController)
    }

    override fun onCharacterClicked(characterId: Int) {
        super.onCharacterClicked(characterId)
        val action =
            CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDetailFragment(
                characterId
            )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
