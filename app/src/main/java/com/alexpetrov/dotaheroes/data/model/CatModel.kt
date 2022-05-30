package com.alexpetrov.dotaheroes.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatModel(

    @SerialName("id")
    val id: String?,
    @SerialName("imageUrl")
    val imageUrl: String?
)
