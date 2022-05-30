package com.alexpetrov.dotaheroes.di

import com.alexpetrov.dotaheroes.data.repository.CatRepository
import com.alexpetrov.dotaheroes.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    factory {
        CatRepository(get())
    }

    viewModel {
        MainViewModel(get())
    }
}