package jp.co.accountant.app.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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

@Composable
fun BaseInput(
    @StringRes titleId: Int,
    placeholder: @Composable (() -> Unit)? = null
) {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.large_space))) {
        Text(
            text = stringResource(titleId),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.medium_space)),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            placeholder = placeholder,
            onValueChange = { newValue ->
                text = newValue
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    IconButton(onClick = {
                        text = ""
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                }
            },
            singleLine = true
        )
    }
}

@Preview
@Composable
private fun PreviewBaseInput() {
    PreviewSurface {
        BaseInput(titleId = R.string.department_title)
    }
}
