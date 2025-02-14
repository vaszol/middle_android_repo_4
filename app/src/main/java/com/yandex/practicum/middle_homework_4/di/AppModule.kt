package com.yandex.practicum.middle_homework_4.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.yandex.practicum.middle_homework_4.data.database.NewsDatabase
import com.yandex.practicum.middle_homework_4.data.news_service.NewsServiceImpl
import com.yandex.practicum.middle_homework_4.data.setting_repository.SettingsRepositoryImpl
import com.yandex.practicum.middle_homework_4.data.work_manager.WorkManagerServiceImp
import com.yandex.practicum.middle_homework_4.ui.AppViewModel
import com.yandex.practicum.middle_homework_4.ui.contract.NewsService
import com.yandex.practicum.middle_homework_4.ui.contract.SettingsRepository
import com.yandex.practicum.middle_homework_4.ui.contract.WorkManagerService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.File

val appModule = module {
    single<NewsService> {
        NewsServiceImpl(
            application = androidContext(),
            fileName = "runews.json"
        )
    }
    single<NewsDatabase> {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java,
            "news_database"
        )
            .build()
    }
    factory<DataStore<Preferences>> {
        provideDataStore(androidContext())
    }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<WorkManagerService> { WorkManagerServiceImp(androidApplication(), get()) }
    single<AppViewModel> { AppViewModel(get(), get(), get(), get()) }
}

fun provideDataStore(context: Context): DataStore<Preferences> {
    val name = "Application setting"
    val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        corruptionHandler = null,
        migrations = emptyList(),
        scope = CoroutineScope(Job() + Dispatchers.IO)
    ) {
        File(context.filesDir, "datastore/$name.preferences_pb")
    }
    return dataStore
}