package jp.co.accountant.app.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import jp.co.accountant.app.ui.theme.AccountantAppTheme

@Composable
fun PreviewSurface(content: @Composable () -> Unit) {
    AccountantAppTheme {
        Surface {
            content()
        }
    }
}
