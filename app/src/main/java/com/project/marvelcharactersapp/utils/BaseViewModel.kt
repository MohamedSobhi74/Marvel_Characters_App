package com.project.marvelcharactersapp.utils

import androidx.lifecycle.ViewModel
import com.project.marvelcharactersapp.network.remote.ErrorMessage
import com.project.marvelcharactersapp.network.remote.ErrorTypes

open class BaseViewModel : ViewModel() {

    private val _errorMessage: SingleMutableLiveData<ErrorMessage> = SingleMutableLiveData()
    val errorMessage get() = _errorMessage

    protected val _isLoading: SingleMutableLiveData<Boolean> = SingleMutableLiveData()
    val isLoading get() = _isLoading

    private val _isAuthError: SingleMutableLiveData<Boolean> = SingleMutableLiveData()
    val isAuthError get() = _isAuthError

    fun handleError(errorTypes: ErrorTypes) {
        _isLoading.value = false
        when (errorTypes) {
            is ErrorTypes.AuthenticationError -> _isAuthError.value = true

            else -> {
                _errorMessage.value = errorTypes.errorMessage
            }
        }
    }


    fun handleLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }
}