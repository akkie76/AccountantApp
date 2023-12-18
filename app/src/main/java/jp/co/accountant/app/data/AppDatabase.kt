package jp.co.accountant.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import jp.co.accountant.app.data.DatabaseWorker.Companion.KEY_FILENAME
import jp.co.accountant.app.data.department.Department
import jp.co.accountant.app.data.department.DepartmentDao
import jp.co.accountant.app.data.history.History
import jp.co.accountant.app.data.history.HistoryDao
import jp.co.accountant.app.data.setting.Setting
import jp.co.accountant.app.data.setting.SettingDao

const val DATABASE_NAME = "accountant_app_db"
const val DATA_FILENAME = "departments_data.json"

@Database(
    entities = [Department::class, History::class, Setting::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun departmentDao(): DepartmentDao
    abstract fun historyDao(): HistoryDao
    abstract fun settingDao(): SettingDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        /**
         * instanceを取得する
         *
         * @param context Context
         * @return AppDatabase
         */
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        /**
         * DatBaseを構築する
         *
         * @return AppDatabase
         */
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<DatabaseWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to DATA_FILENAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}
