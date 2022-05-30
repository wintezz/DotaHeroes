package com.alexpetrov.dotaheroes.data

import com.alexpetrov.dotaheroes.data.model.CatModel

interface NetworkSource {
    suspend fun getUsers(resultCount :Int ) : CatModel
}