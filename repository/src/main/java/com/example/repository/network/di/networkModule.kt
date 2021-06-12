package com.example.repository.network.di

import com.example.repository.network.api.MoviesNetwork
import com.example.usecase.repository.network.movies.MoviesNetworkRepository
import org.koin.dsl.module

val networkModule = module {
    single<MoviesNetworkRepository> { MoviesNetwork(get()) }
}