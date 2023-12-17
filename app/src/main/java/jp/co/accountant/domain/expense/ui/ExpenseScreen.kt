package jp.co.accountant.domain.expense.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import jp.co.accountant.R
import jp.co.accountant.app.ui.BaseInput
import jp.co.accountant.app.ui.DatePickerInput
import jp.co.accountant.app.ui.theme.AccountantAppTheme
import jp.co.accountant.domain.expense.ui.component.AmountMoneyInput
import jp.co.accountant.domain.expense.ui.component.DepartmentInput
import jp.co.accountant.domain.expense.ui.component.InvoiceInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.app_bar_title)) },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .padding(horizontal = dimensionResource(R.dimen.large_space))
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                /** 取引先名 **/
                BaseInput(titleId = R.string.customer_name_title)

                /** 取引日 **/
                DatePickerInput(titleId = R.string.trading_date_title)

                /** 受領日 **/
                DatePickerInput(titleId = R.string.receipt_date_title)

                /** 事業者登録番号 **/
                InvoiceInput {
                }

                /** 金額 **/
                AmountMoneyInput()

                /** 負担部門 **/
                DepartmentInput()

                /** 備考 **/
                BaseInput(titleId = R.string.others_title)
            }
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
