package jp.co.accountant.domain.expense.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import jp.co.accountant.R
import jp.co.accountant.app.ui.theme.AccountantAppTheme
import jp.co.accountant.domain.expense.ui.component.DepartmentInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "経費申請") })
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .padding(horizontal = dimensionResource(R.dimen.large_space))
        ) {
            DepartmentInput()
        }
    }
}

@Preview
@Composable
private fun PreviewExpenseScreen() {
    AccountantAppTheme {
        ExpenseScreen()
    }
}
