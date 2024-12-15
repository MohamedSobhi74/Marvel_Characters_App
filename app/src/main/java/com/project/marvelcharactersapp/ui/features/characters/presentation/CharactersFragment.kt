package com.project.marvelcharactersapp.ui.features.characters.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.project.marvelcharactersapp.ui.adapters.CharactersAdapter
import com.project.marvelcharactersapp.databinding.FragmentCharactersBinding
import com.project.marvelcharactersapp.ui.features.characters.data.model.Character
import com.project.marvelcharactersapp.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharactersFragment :
    BaseFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate),
    CharactersAdapter.OnItemClick {


    override val viewModel: CharactersViewModel by viewModels()
    private var list: List<Character?> = mutableListOf()
    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter(
            requireContext()
        )
    }


    private fun navigateToSearchScreen() {
        val direction = CharactersFragmentDirections.actionCharactersFragmentToSearchFragment()
        findNavController().navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.searchIcon.setOnClickListener {
            navigateToSearchScreen()
        }

         setupCharactersRecyclerView()
    }

    private fun setupCharactersRecyclerView() {
        binding.charactersRecyclerView.adapter = adapter

        viewModel.marvelCharacters.observe(viewLifecycleOwner) {
            list = it!!.results
            adapter.submitList(list)
        }

        adapter.listener = { _, item, _ ->
            val direction =
                CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(
                    character = item
                )
            findNavController().navigate(direction)
        }
        viewModel.getMarvelCharacters()
    }


    override fun onItemClick(building: Int) {
        TODO("Not yet implemented")
    }
}