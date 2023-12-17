package jp.co.accountant.domain.expense.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import jp.co.accountant.R
import jp.co.accountant.app.ui.CommaSeparatorInput
import jp.co.accountant.app.ui.PreviewSurface
import jp.co.accountant.domain.expense.data.AmountMoney

@Composable
fun AmountMoneyInput(
    eightPercentText: String = "0",
    tenPercentText: String = "0",
    onValueChange: (AmountMoney) -> Unit = {}
) {
    var eightPercentAmount by remember { mutableStateOf(eightPercentText) }
    var tenPercentAmount by remember { mutableStateOf(tenPercentText) }

    Column {
        CommaSeparatorInput(
            text = eightPercentAmount,
            titleId = R.string.eight_percent_money_title,
            supportingTextId = R.string.eight_percent_money_supporting_text,
            onValueChange = { newValue ->
                eightPercentAmount = newValue
                onValueChange(AmountMoney(eightPercentAmount, tenPercentAmount))
            }
        )

        CommaSeparatorInput(
            text = tenPercentAmount,
            titleId = R.string.ten_percent_money_title,
            supportingTextId = R.string.ten_percent_money_supporting_text,
            onValueChange = { newValue ->
                tenPercentAmount = newValue
                onValueChange(AmountMoney(eightPercentAmount, tenPercentAmount))
            }
        )
    }
}

@Preview
@Composable
private fun PreviewAmountMoneyInput() {
    PreviewSurface {
        AmountMoneyInput()
    }
}