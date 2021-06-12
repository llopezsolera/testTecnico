package com.example.usecase.di

import com.example.usecase.usecases.MoviesUseCase
import org.koin.dsl.module

val usecasesModule = module {
    single{ MoviesUseCase(get()) }
}