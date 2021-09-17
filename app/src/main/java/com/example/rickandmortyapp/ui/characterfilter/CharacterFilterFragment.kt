package com.example.rickandmortyapp.ui.characterfilter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.FragmentCharacterFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CharacterFilterFragment(private val callback: ICharacterFilter) : BottomSheetDialogFragment() {
    private var _binding: FragmentCharacterFilterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterFilterBinding.inflate(inflater, container, false)
        val view = binding.root
        initView()
        return view
    }

    private fun initView() {
        setQuerySpinners()
        setQueryButton()
    }

    private fun setQueryButton() {
        binding.apply {
            queryBottomSheetQueryButton.setOnClickListener {
                val name = queryBottomSheetEditText.text.toString()
                val status = allSelectedCheck(binding.queryBottomSheetStatusSpinner.selectedItem.toString())
                val gender = allSelectedCheck(binding.queryBottomSheetGenderSpinner.selectedItem.toString())

                callback.filterValue(name, status, gender)

                dismiss()
            }
        }
    }

    private fun allSelectedCheck(selectedItemText: String): String? {
        if (selectedItemText != "All"){
            return selectedItemText.lowercase()
        }else{
            return null
        }
    }

    private fun setQuerySpinners() {
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.status_spinner,
                android.R.layout.simple_spinner_item
            ).also { adapter ->

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.queryBottomSheetStatusSpinner.adapter = adapter
            }
            ArrayAdapter.createFromResource(
                it,
                R.array.gender_spinner,
                android.R.layout.simple_spinner_item
            ).also { adapter ->

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.queryBottomSheetGenderSpinner.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}