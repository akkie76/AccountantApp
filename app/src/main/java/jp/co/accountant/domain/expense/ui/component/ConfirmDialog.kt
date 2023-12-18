package jp.co.accountant.domain.expense.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import jp.co.accountant.R
import jp.co.accountant.app.ui.PreviewSurface
import jp.co.accountant.app.ui.theme.success

@Composable
fun ConfirmDialog(onConfirmation: () -> Unit = {}) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                modifier = Modifier.size(dimensionResource(R.dimen.mini_icon_size)),
                contentDescription = null,
                tint = success
            )
        },
        title = {
        },
        text = {
            Text(text = stringResource(R.string.confirm_register))
        },
        onDismissRequest = {},
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = stringResource(R.string.ok))
            }
        },
        dismissButton = {
        }
    )
}

@Preview
@Composable
private fun PreviewConfirmDialog() {
    PreviewSurface {
        ConfirmDialog()
    }
}
