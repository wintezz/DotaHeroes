package com.alexpetrov.dotaheroes.di

import com.alexpetrov.dotaheroes.data.CatApi
import com.alexpetrov.dotaheroes.data.NetworkSource
import org.koin.dsl.module

val networkModule = module {
    single<NetworkSource>{
        CatApi()
    }
}