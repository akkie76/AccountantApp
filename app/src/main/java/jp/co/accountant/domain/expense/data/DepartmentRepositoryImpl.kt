package jp.co.accountant.domain.expense.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class DepartmentRepositoryImpl @Inject constructor() : DepartmentRepository {

    private val databaseReference = FirebaseDatabase
        .getInstance(DATABASE_URL).getReference("department")

    private var lastUserId: Int = 0

    // query: String, page: Int, perPage: Int
    // タブごとに全件取得するか
    // 履歴から取得する場合は完全一致で検索する
    override suspend fun fetchData(): List<Department> = suspendCoroutine { continuation ->
        // 一文字クエリ検索するよりは通信量は少なくなる
        val query = databaseReference
            .orderByChild("id")
            //.orderByChild("name")  // 検索対象のカラム名
            //.equalTo("Mike") // 部分一致はサポートしていない
            // orderByChildとstartAfterは同時に設定できない
            .startAfter(lastUserId.toDouble())
            .limitToFirst(30) //おまけ

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val departments = mutableListOf<Department>()

                for (snapshot in dataSnapshot.children) {
                    val id = snapshot.child("id").getValue(Int::class.java)
                    val name = snapshot.child("name").getValue(String::class.java)

                    if (id != null && name != null) {
                        println("id: $id, name: $name")
                        lastUserId = id
                        departments.add(Department(id, name))
                    }
                }

                continuation.resume(departments)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                continuation.resumeWithException(databaseError.toException())
            }
        })
    }

    companion object {
        private const val DATABASE_URL = "https://accountantapp-f6f1c-default-rtdb.firebaseio.com/"
    }
}
