package com.example.cardinfo.di

import android.content.Context
import com.example.cardinfo.ui.HistoryFragment
import com.example.cardinfo.ui.SearchFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AllModules::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(fragment: HistoryFragment)
    fun inject(fragment: SearchFragment)

}