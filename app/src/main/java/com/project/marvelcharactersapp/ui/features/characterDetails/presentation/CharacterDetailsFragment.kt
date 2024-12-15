package com.project.marvelcharactersapp.ui.features.characterDetails.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.marvelcharactersapp.R
import com.project.marvelcharactersapp.ui.adapters.ArtworkAdapter
import com.project.marvelcharactersapp.ui.adapters.CharactersAdapter
import com.project.marvelcharactersapp.databinding.FragmentCharacterDetailsBinding
import com.project.marvelcharactersapp.utils.BaseFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailsFragment :
    BaseFragment<FragmentCharacterDetailsBinding>(FragmentCharacterDetailsBinding::inflate),
    CharactersAdapter.OnItemClick {


    override val viewModel: CharacterDetailsViewModel by viewModels()
    private val args: CharacterDetailsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.character = args.character
        binding.backBtn.setOnClickListener { requireActivity().onBackPressed()}
        Picasso
            .get()
            .load(args.character!!.thumbnail.getImageUrl())
            .placeholder(R.drawable.marvel_logo)
            .error(R.drawable.marvel_logo)
            .into(binding.characterImg)

        setupComicsRecyclerView()
        setupSeriesRecyclerView()
        setupEventsRecyclerView()
        setupStoriesRecyclerView()


    }

    private fun setupComicsRecyclerView() {
        val comicsAdapter: ArtworkAdapter by lazy {
            ArtworkAdapter(requireContext()) { resourceUrl, callback ->
                viewModel.getArtworkResource(resourceUrl)
                viewModel.artworkResources.observe(viewLifecycleOwner) { artworkResources ->
                    callback(artworkResources[resourceUrl])
                }
            }
        }

        binding.comicsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = comicsAdapter
        }

        comicsAdapter.submitList(args.character!!.comics!!.items)
    }
    private fun setupEventsRecyclerView() {
        val comicsAdapter: ArtworkAdapter by lazy {
            ArtworkAdapter(requireContext()) { resourceUrl, callback ->
                viewModel.getArtworkResource(resourceUrl)
                viewModel.artworkResources.observe(viewLifecycleOwner) { artworkResources ->
                    callback(artworkResources[resourceUrl])
                }
            }
        }

        binding.eventsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = comicsAdapter
        }

        comicsAdapter.submitList(args.character!!.events!!.items)
    }

    private fun setupSeriesRecyclerView() {
        val comicsAdapter: ArtworkAdapter by lazy {
            ArtworkAdapter(requireContext()) { resourceUrl, callback ->
                viewModel.getArtworkResource(resourceUrl)
                viewModel.artworkResources.observe(viewLifecycleOwner) { artworkResources ->
                    callback(artworkResources[resourceUrl])
                }
            }
        }

        binding.seriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = comicsAdapter
        }

        comicsAdapter.submitList(args.character!!.series!!.items)
    }
    private fun setupStoriesRecyclerView() {
        val comicsAdapter: ArtworkAdapter by lazy {
            ArtworkAdapter(requireContext()) { resourceUrl, callback ->
                viewModel.getArtworkResource(resourceUrl)
                viewModel.artworkResources.observe(viewLifecycleOwner) { artworkResources ->
                    callback(artworkResources[resourceUrl])
                }
            }
        }

        binding.storiesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = comicsAdapter
        }

        comicsAdapter.submitList(args.character!!.stories!!.items)
    }


    override fun onItemClick(building: Int) {
        TODO("Not yet implemented")
    }
}