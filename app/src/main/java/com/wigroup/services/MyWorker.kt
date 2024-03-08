package com.wigroup.services

import android.content.Context
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class MyWorker(
    context: Context,
    private val workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        println("doWork")
        val page = workerParams.inputData.getInt(PAGE, 1)
        for (i in 0 until 5) {
            Thread.sleep(1000)
            println("Timer: $i, Page: $page")
        }

        return Result.success()
    }

    companion object {

        private const val PAGE = "page"
        const val WORK_NAME = "MyWorker"

        fun makeRequest(page: Int) = OneTimeWorkRequestBuilder<MyWorker>()
            .setInputData(workDataOf(PAGE to page))
            .setConstraints(
                Constraints.Builder()
                    .setRequiresCharging(true)
                    .build()
            )
            .build()
    }
}