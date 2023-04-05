package com.toru.referead.repository.utils

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.toru.referead.remote.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class NetworkBoundResource<ResultType, RequestType> {
    @ExperimentalCoroutinesApi
    fun asFlow() = flow<Resource<ResultType>> {
        emit(Resource.loading(null))

        try {
            Log.d("NetworkBoundResource", "Fetch data from network")
            val apiResponse = fetchFromNetwork()
            Log.d("NetworkBoundResource", "Data fetched from network: $apiResponse")
            val error = isError(apiResponse)
            if (error != null) {
                Log.d("NetworkBoundResource", "emit error: ${error.message}")
                emit(Resource.error(error, null))
            } else {
                Log.d("NetworkBoundResource", "emit success")
                emit(Resource.success(processResponse(apiResponse)))
            }
        } catch (e: Exception) {
            Log.d("NetworkBoundResource", "An error happened: $e")
            emit(Resource.error(e, null))
        }
    }.flowOn(Dispatchers.Default) // ^ works on the flow before it

    protected open fun isError(response: RequestType): Throwable? {
        return null
    }

    @WorkerThread
    protected abstract fun processResponse(response: RequestType): ResultType

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): RequestType
}