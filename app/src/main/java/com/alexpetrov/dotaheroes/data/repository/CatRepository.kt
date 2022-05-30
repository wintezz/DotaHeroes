package com.alexpetrov.dotaheroes.data.repository


import com.alexpetrov.dotaheroes.data.NetworkSource
import com.alexpetrov.dotaheroes.data.model.CatModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatRepository(
    private val randomUserApi: NetworkSource
) {

    fun getUsers(resultListCount: Int): Flow<List<Person>> = flow {
        val userResultDTO: CatModel = randomUserApi.getUsers(resultListCount)
        val peopleList = mutableListOf<Person>()
        userResultDTO.results?.forEach {
            peopleList.add(
                it.toPerson()
            )
        }
        emit(peopleList)
    }

    private fun Result.toPerson(): Person {
        return Person(
            id = this.id?.value ?: "",
            phoneNumber = this.phone ?: "",
            profilePhotoUrl = this.picture?.thumbnail ?: "",
            fullName = this.name?.getFullName() ?: ""
        )
    }

    private fun Name.getFullName(): String {
        return "$first $last"
    }

}