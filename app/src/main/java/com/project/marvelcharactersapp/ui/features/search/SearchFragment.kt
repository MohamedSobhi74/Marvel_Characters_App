package com.project.marvelcharactersapp.ui.features.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.project.marvelcharactersapp.databinding.FragmentSearchBinding
import com.project.marvelcharactersapp.ui.adapters.SearchCharactersAdapter
import com.project.marvelcharactersapp.ui.features.characters.data.model.Character
import com.project.marvelcharactersapp.ui.features.characters.presentation.CharactersViewModel
import com.project.marvelcharactersapp.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :  BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate), SearchView.OnQueryTextListener {


    override val viewModel: CharactersViewModel by viewModels()
    private var list: List<Character?> = mutableListOf()
    private val adapter: SearchCharactersAdapter by lazy {
        SearchCharactersAdapter(
            requireContext()
        )
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.cancelSearch.setOnClickListener { findNavController().navigateUp() }

        binding.characterSearchView.setOnQueryTextListener(this)

        setupSearchCharactersRecyclerView()
    }


    private fun setupSearchCharactersRecyclerView() {
        binding.charactersRecyclerView.adapter = adapter

        viewModel.marvelCharacters.observe(viewLifecycleOwner) {
            list = it!!.results
            adapter.submitList(list)
        }

        adapter.listener = { _, item, _ ->
            /*val direction = PrivateSectorFragmentDirections.actionToSelectMeterFragment(item)
            findNavController().navigate(direction)*/
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.getMarvelCharacters(newText)
        return true
    }
}