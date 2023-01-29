package com.example.cardinfo.di

import android.content.Context
import com.example.cardinfo.CardApplication
import com.example.cardinfo.ui.HistoryFragment
import com.example.cardinfo.ui.SearchFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        RoomModule::class,
        ActivityModel::class,
        RepositoryModule::class,
        ViewModelModule::class,
        SearchFragmentModule::class,
        HistoryFragmentModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(cardApplication: CardApplication)

    fun inject(searchFragment: SearchFragment)
    fun inject(historyFragment: HistoryFragment)
}