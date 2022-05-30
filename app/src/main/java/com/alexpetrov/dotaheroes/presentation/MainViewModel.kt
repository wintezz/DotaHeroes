package com.alexpetrov.dotaheroes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexpetrov.dotaheroes.data.model.CatModel
import com.alexpetrov.dotaheroes.data.repository.CatRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private typealias PeopleList = List<CatModel>

class MainViewModel(
    private val randomPersonRepository: CatRepository
) : ViewModel() {

    private val _sharedFlow = MutableSharedFlow<PeopleList>(
        replay = 1,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    val peopleListFlow : SharedFlow<PeopleList> = _sharedFlow

    fun getRandomUsers(resultListCount : Int ) = viewModelScope.launch {
        _sharedFlow.emitAll(
            randomPersonRepository.getUsers(resultListCount)
        )

    }




}