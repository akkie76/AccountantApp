package jp.co.accountant.app.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import jp.co.accountant.R

@Composable
fun BaseListItem(
    modifier: Modifier = Modifier,
    headLine: String,
    supportingText: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.list_height))
    ) {
        leadingIcon?.let { component ->
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.medium_space)))
            component()
        }
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.medium_space)))
        Text(
            text = headLine,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = supportingText,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = dimensionResource(R.dimen.large_space))
        )
        trailingIcon?.let { component ->
            component()
        } ?: run {
            Spacer(
                modifier = Modifier
                    .width(dimensionResource(R.dimen.mini_icon_size))
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.medium_space)))
    }
}
