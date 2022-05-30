package com.alexpetrov.dotaheroes

interface Listener{
    fun onClickItem(heroModel: List<HeroModel>, position: Int)
}
