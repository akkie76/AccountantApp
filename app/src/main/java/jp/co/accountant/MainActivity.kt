package jp.co.accountant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import jp.co.accountant.app.ui.theme.AccountantAppTheme
import jp.co.accountant.domain.expense.ui.ExpenseScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccountantAppTheme {
                ExpenseScreen()
            }
        }
    }
}
