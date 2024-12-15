package com.project.marvelcharactersapp.ui.features.characterDetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.marvelcharactersapp.network.remote.Resource
import com.project.marvelcharactersapp.ui.features.characterDetails.domain.CharacterDetailsRepo
import com.project.marvelcharactersapp.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterDetailsRepo: CharacterDetailsRepo,
) : BaseViewModel() {

    // A map to store image URLs for each resourceURI
    private val _artworkResources = MutableLiveData<Map<String, String?>>()
    val artworkResources: LiveData<Map<String, String?>> get() = _artworkResources

    fun getArtworkResource(resourceUrl: String) {
        characterDetailsRepo.getArtworkResource(resourceUrl)
            .onStart {
                // Optionally handle loading state
            }
            .map {
                when (it) {
                    is Resource.Error -> {
                        handleLoading(false)
                        handleError(it.errorTypes)
                    }

                    is Resource.Success -> {
                        handleLoading(false)
                        val imageUrl = it.data?.data?.results?.get(0)?.thumbnail?.let {
                            "${it.path}.${it.extension}"
                        }

                        // Update the image URL for the specific resource
                        val currentMap = _artworkResources.value.orEmpty()
                        _artworkResources.value = currentMap + (resourceUrl to imageUrl)
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}

