package jp.co.accountant.app.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// TODO: @Suppress
@Suppress("BlockingMethodInNonBlockingContext")
class DatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val companyType = object : TypeToken<List<Department>>() {}.type
                        val departments: List<Department> = Gson().fromJson(jsonReader, companyType)
                        val database = AppDatabase.getInstance(applicationContext)
                        database.departmentDao().insertAll(departments)
                        Result.success()
                    }
                }
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }

    companion object {
        const val KEY_FILENAME = "data_file_name"
    }
}
