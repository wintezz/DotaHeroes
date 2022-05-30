package com.alexpetrov.dotaheroes.data

import com.alexpetrov.dotaheroes.data.model.CatModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

private const val BaseUrl: String = "https://randomuser.me/api"

class CatApi : NetworkSource {

    private val client by lazy {
        HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
        }
    }

    fun <R> useClient(block: (HttpClient) -> R) = client.use {
        block(it)
    }

    override suspend fun getUsers(resultCount: Int): CatModel {
        return client.get(
            urlString = "$BaseUrl/?results=$resultCount"
        )
    }
}
}