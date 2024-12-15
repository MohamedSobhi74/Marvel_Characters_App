package com.project.marvelcharactersapp.network.remote

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T?) : Resource<T>()
    data class Error(val errorTypes: ErrorTypes) : Resource<Nothing>()
}