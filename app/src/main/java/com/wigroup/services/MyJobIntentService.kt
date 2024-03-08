package com.wigroup.services

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService

class MyJobIntentService : JobIntentService() {

    override fun onCreate() {
        super.onCreate()
        println("onCreate")
    }

    override fun onHandleWork(intent: Intent) {
        println("onHandleWork")
        val page = intent.getIntExtra(PAGE, 0)
        for (i in 0 until 5) {
            Thread.sleep(1000)
            println("Timer: $i, Page: $page")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

    companion object {

        private const val PAGE = "page"
        private const val JOB_ID = 1

        private fun newIntent(context: Context, page: Int): Intent =
            Intent(context, MyJobIntentService::class.java).apply {
                putExtra(PAGE, page)
            }

        fun enqueue(context: Context, page: Int) {
            enqueueWork(
                context,
                MyJobIntentService::class.java,
                JOB_ID,
                newIntent(context, page)
            )
        }
    }
}