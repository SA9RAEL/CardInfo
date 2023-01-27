package com.example.cardinfo.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RoomModule::class])
interface AppComponent {

    fun build(): AppComponent

}