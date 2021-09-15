package com.example.rickandmortyapp.ui.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.character.CharacterResponse
import com.example.rickandmortyapp.databinding.ItemCharacterListBinding

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    private var characterList = ArrayList<CharacterResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val binding = ItemCharacterListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val character = characterList[position]
        holder.binding.apply {
            characterNameTextView.text = character.name

            Glide
                .with(holder.itemView.context)
                .load(character.image)
                .into(characterImageView)

            when (character.gender) {
                "Male" -> genderImageView.setImageResource(R.drawable.ic_male)
                "Female" -> genderImageView.setImageResource(R.drawable.ic_female)
                else -> genderImageView.setImageResource(R.drawable.ic_genderless)
            }

            when (character.status) {
                "Alive" -> aliveImageView.setImageResource(R.drawable.ic_alive)
                "Dead" -> aliveImageView.setImageResource(R.drawable.ic_dead)
                else -> aliveImageView.setImageResource(R.drawable.ic_unknown)
            }
        }

        holder.binding.itemCardView.setOnClickListener{
            val action =
                CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDetailFragment(
                    character.id
                )
            it.findNavController().navigate(action)
        }
    }

    fun setCharactersList(characters: ArrayList<CharacterResponse>) {
        characterList = characters
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = characterList.size

    inner class CharacterListViewHolder(val binding: ItemCharacterListBinding) :
        RecyclerView.ViewHolder(binding.root)
}