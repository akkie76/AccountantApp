package jp.co.accountant.domain.expense.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class DepartmentRepositoryImpl @Inject constructor() : DepartmentRepository {

    private val databaseReference = FirebaseDatabase
        .getInstance(DATABASE_URL).getReference("user")

    private var lastState: String? = null
    private var lastKey: String? = null

    override fun fetchData() {
        val query = databaseReference.orderByChild("id").limitToFirst(20)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val userId = snapshot.key
                    val userName = snapshot.child("name").getValue(String::class.java)
                    println("id: $userId, name: $userName")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException()
            }
        })
    }

    companion object {
        private const val DATABASE_URL = "https://accountantapp-f6f1c-default-rtdb.firebaseio.com/"
    }
}
