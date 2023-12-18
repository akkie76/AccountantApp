package jp.co.accountant.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import jp.co.accountant.R

@Composable
fun DescriptionText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(top = dimensionResource(R.dimen.small_space)),
        style = MaterialTheme.typography.bodySmall
    )
}
