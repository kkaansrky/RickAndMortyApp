package com.example.rickandmortyapp.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.rickandmortyapp.data.character.CharacterResponse
import com.example.rickandmortyapp.databinding.FragmentCharacterDetailBinding
import com.example.rickandmortyapp.utils.Resource
import com.example.rickandmortyapp.utils.hide
import com.example.rickandmortyapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private var _binding: FragmentCharacterDetailBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by viewModels()

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        initView()
        return view
    }

    private fun initView() {
        getCharacterDetail(args.characterId)
    }

    private fun getCharacterDetail(characterId : Int) {
        viewModel.getCharacterById(characterId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()

                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.hide()

                    val character = it.data
                    if (character != null) {
                        updateUI(character)
                    }


                }
                Resource.Status.ERROR -> {
                    binding.progressBar.hide()

                }
            }
        })
    }

    private fun updateUI(character: CharacterResponse) {
        val epoxyController = CharacterDetailEpoxyController()

        epoxyController.character = character
        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}