package com.project.marvelcharactersapp.ui.features.characters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.project.marvelcharactersapp.network.remote.Resource
import com.project.marvelcharactersapp.ui.features.characters.data.model.MarvelCharactersResponse
import com.project.marvelcharactersapp.ui.features.characters.domain.CharactersRepo
import com.project.marvelcharactersapp.utils.BaseViewModel
import com.project.marvelcharactersapp.utils.SingleMutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepo: CharactersRepo,
) : BaseViewModel() {

    private val _marvelCharacters = SingleMutableLiveData<MarvelCharactersResponse?>()
    val marvelCharacters: LiveData<MarvelCharactersResponse?> get() = _marvelCharacters

     fun getMarvelCharacters(
         query: String? = null

     ) {
         charactersRepo.getMarvelCharacters(query)
            .onStart {
                handleLoading(true)
            }.map {
                when (it) {
                    is Resource.Error -> {
                        handleLoading(false)
                        handleError(it.errorTypes)
                    }

                    is Resource.Success -> {
                        handleLoading(false)
                        _marvelCharacters.value = it.data!!.data
                    }
                }
            }.launchIn(viewModelScope)
    }
}