package jp.co.accountant.app.ui

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import jp.co.accountant.R
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseDatePickerDialog(
    onConfirm: (String) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )

    DatePickerDialog(
        onDismissRequest = {
        },
        confirmButton = {
            TextButton(onClick = {
                val selectedDateMillis = datePickerState.selectedDateMillis ?: return@TextButton
                val selectedDate = Instant.ofEpochMilli(selectedDateMillis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .format(DateTimeFormatter.ofPattern(FORMAT_PATTERN))
                onConfirm(selectedDate)
            }) {
                Text(text = stringResource(R.string.ok))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            title = null,
            headline = null,
            showModeToggle = false
        )
    }
}

private const val FORMAT_PATTERN = "yyyy/MM/dd"

@Preview
@Composable
private fun PreviewDatePickerDialog() {
    PreviewSurface {
        BaseDatePickerDialog()
    }
}
