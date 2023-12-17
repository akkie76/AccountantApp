package jp.co.accountant.domain.expense.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import jp.co.accountant.R
import jp.co.accountant.app.ui.BaseInput
import jp.co.accountant.app.ui.DatePickerInput
import jp.co.accountant.app.ui.theme.AccountantAppTheme
import jp.co.accountant.domain.expense.ui.component.AmountMoneyInput
import jp.co.accountant.domain.expense.ui.component.DepartmentInput
import jp.co.accountant.domain.expense.ui.component.ExpenseTopAppBar
import jp.co.accountant.domain.expense.ui.component.InvoiceInput

@Composable
fun ExpenseScreen() {
    var customerName by remember { mutableStateOf("") }
    var tradingDate by remember { mutableStateOf("") }
    var receiptDate by remember { mutableStateOf("") }
    var invoiceBusinessNumber by remember { mutableStateOf("") }
    var eightPercentAmount by remember { mutableStateOf("0") }
    var tenPercentAmount by remember { mutableStateOf("0") }
    var department by remember { mutableStateOf("") }
    var others by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            ExpenseTopAppBar()
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .padding(horizontal = dimensionResource(R.dimen.large_space))
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                /** 取引先名 **/
                BaseInput(
                    text = customerName,
                    titleId = R.string.customer_name_title,
                    onValueChange = { newValue ->
                        customerName = newValue
                    }
                )

                /** 取引日 **/
                DatePickerInput(
                    text = tradingDate,
                    titleId = R.string.trading_date_title,
                    onValueChange = { newValue ->
                        tradingDate = newValue
                    }
                )

                /** 受領日 **/
                DatePickerInput(
                    text = receiptDate,
                    titleId = R.string.receipt_date_title,
                    onValueChange = { newValue ->
                        receiptDate = newValue
                    }
                )

                /** 事業者登録番号 **/
                InvoiceInput(
                    text = invoiceBusinessNumber,
                    onValueChange = { newValue ->
                        invoiceBusinessNumber = newValue
                    }
                )

                /** 金額 **/
                AmountMoneyInput(
                    eightPercentText = eightPercentAmount,
                    tenPercentText = tenPercentAmount,
                    onValueChange = { amount ->
                        eightPercentAmount = amount.eightPercent
                        tenPercentAmount = amount.tenPercent
                    }
                )

                /** 負担部門 **/
                DepartmentInput(
                    text = department,
                    onValueChange = { newValue ->
                        department = newValue
                    }
                )

                /** 備考 **/
                BaseInput(
                    text = others,
                    titleId = R.string.others_title,
                    onValueChange = { newValue ->
                        others = newValue
                    }
                )

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(R.dimen.button_vertical))
                ) {
                    Text(
                        text = stringResource(R.string.register),
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedButton(
                    onClick = {
                        customerName = ""
                        tradingDate = ""
                        receiptDate = ""
                        invoiceBusinessNumber = ""
                        eightPercentAmount = "0"
                        tenPercentAmount = "0"
                        department = ""
                        others = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(R.dimen.large_space))
                        .padding(bottom = dimensionResource(R.dimen.button_vertical))
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        fontWeight = FontWeight.Bold
                    )
                }
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
