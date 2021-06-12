package com.example.usecase.repository.network

sealed class OperationResult<out T> {
    data class Success<T>(val data: T?) : OperationResult<T>()
    data class Error(val exception: NetworkMessage?) : OperationResult<Nothing>()
}