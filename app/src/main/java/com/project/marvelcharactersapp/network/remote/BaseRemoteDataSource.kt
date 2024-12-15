package com.project.marvelcharactersapp.network.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest

open class BaseRemoteDataSource {
    fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
    ): Flow<Resource<T>> = flow {
        emit(safeApiResult(call))
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): Resource<T> {
        var response: Response<T>? = null

        try {
            response = call.invoke()
        } catch (_: Exception) {
            return getResultError(null)
        }

        if (response.isSuccessful) {
            return Resource.Success(response.body())
        }

        return getResultError(response)
    }

    private fun <T : Any> getResultError(response: Response<T>?): Resource<T> {
        return when (response?.code()) {

            401 -> {
                Resource.Error(ErrorTypes.AuthenticationError())
            }

            in 402..499, 15 -> {
                val message: String = try {
                    val jObjError = JSONObject(response?.errorBody()?.string().orEmpty())
                    val statusObj = jObjError.optJSONObject("Status")
                    statusObj?.optString("Message").orEmpty()
                } catch (_: Exception) {
                    "Error happened, try again."
                }

                Resource.Error(
                    ErrorTypes.NetworkError(
                        ErrorMessage.DynamicString(message)
                    )
                )
            }

            500 -> {
                Resource.Error(
                    ErrorTypes.NetworkError(ErrorMessage.DynamicString("Opps, unknown error happened, please try again later"))
                )
            }

            else -> {
                val message = response?.errorBody()?.string().orEmpty()

                if (message.isEmpty()) {
                    Resource.Error(
                        ErrorTypes.GeneralError(
                            ErrorMessage.DynamicString("Unknown error, please check your internet and try again.")
                        )
                    )
                } else {
                    Resource.Error(ErrorTypes.GeneralError(ErrorMessage.DynamicString(message)))
                }
            }
        }
    }

    fun createHash(input: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        return BigInteger(1, md5.digest(input.toByteArray()))
            .toString(16).padStart(32, '0')
    }
}