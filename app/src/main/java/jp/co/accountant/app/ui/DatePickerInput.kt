package jp.co.accountant.app.ui

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jp.co.accountant.R

@Composable
fun DatePickerInput(
    text: String = "",
    @StringRes titleId: Int,
    onValueChange: (String) -> Unit = {}
) {
    var date by remember { mutableStateOf(text) }
    var showDatePicker by remember { mutableStateOf(false) }

    BaseInput(
        modifier = Modifier.clickable {
            showDatePicker = true
        },
        text = date,
        enabled = false,
        titleId = titleId,
        // FIXME: disabledIndicatorColorがやや異なるので修正する
        colors = TextFieldDefaults.colors(
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )

    if (showDatePicker) {
        BaseDatePickerDialog(
            onConfirm = { selectedDate ->
                date = selectedDate
                onValueChange(date)
                showDatePicker = false
            },
            onDismiss = {
                showDatePicker = false
            }
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
private fun PreviewDatePickerInput() {
    Scaffold {
        PreviewSurface {
            DatePickerInput(titleId = R.string.trading_date_title)
        }
    }
}
