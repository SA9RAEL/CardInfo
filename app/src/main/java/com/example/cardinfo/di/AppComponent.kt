package com.example.cardinfo.di

import android.content.Context
import com.example.cardinfo.ui.SearchFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RoomModule::class, RepositoryModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(searchFragment: SearchFragment)

}