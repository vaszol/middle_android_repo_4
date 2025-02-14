package com.yandex.practicum.middle_homework_4.ui.contract

import com.yandex.practicum.middle_homework_4.data.setting_repository.SettingContainer
import kotlinx.coroutines.flow.StateFlow

interface SettingsRepository {
    val state: StateFlow<SettingContainer>
    suspend fun saveSetting(periodic: Long, delayed: Long)
    suspend fun readSetting()
}