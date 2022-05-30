package com.alexpetrov.dotaheroes.domain

import com.alexpetrov.dotaheroes.data.HeroModel

interface Listener{
    fun onClickItem(heroModel: List<HeroModel>, position: Int)
}
