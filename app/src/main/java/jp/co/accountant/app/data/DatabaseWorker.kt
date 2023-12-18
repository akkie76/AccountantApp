package jp.co.accountant.app.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import jp.co.accountant.app.data.department.Department
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.InputStreamReader

class DatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    InputStreamReader(inputStream).use { streamReader ->
                        val departments: List<Department> = Json.decodeFromString(streamReader.readText())
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
