package com.example.apppruebaindra.di

import com.example.apppruebaindra.ui.Movies.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModulesModule = module {
    viewModel { MoviesViewModel(get()) }
}