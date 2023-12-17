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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
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
    modifier: Modifier = Modifier,
    text: String = "",
    @StringRes titleId: Int,
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    placeholder: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
) {
    var input by remember { mutableStateOf(text) }

    Column(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.large_space))) {
        Text(
            text = stringResource(titleId),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.medium_space)),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = text,
            modifier = modifier.fillMaxWidth(),
            enabled = enabled,
            placeholder = placeholder,
            onValueChange = { newValue ->
                input = newValue
                onValueChange(input)
            },
            trailingIcon = {
                if (text.isNotEmpty() && enabled) {
                    IconButton(onClick = {
                        input = ""
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                }
            },
            supportingText = supportingText,
            singleLine = true,
            colors = colors
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
