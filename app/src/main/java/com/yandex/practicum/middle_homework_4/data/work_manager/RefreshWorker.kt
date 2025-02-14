package com.yandex.practicum.middle_homework_4.data.work_manager


import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.yandex.practicum.middle_homework_4.ui.AppViewModel
import org.koin.core.context.GlobalContext.get


class RefreshWorker(
    context: Context,
    workParams: WorkerParameters
) : CoroutineWorker(context, workParams) {

    private val appViewModel: AppViewModel by get().inject()

    override suspend fun doWork(): Result {
        appViewModel.refreshData()
        return Result.success()
    }
}