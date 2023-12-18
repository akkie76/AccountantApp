package jp.co.accountant.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import jp.co.accountant.R

@Composable
fun InputTitle(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.medium_space)),
        style = TextStyle(
            fontSize = dimensionResource(R.dimen.input_title_font_size).value.sp,
            lineHeight = dimensionResource(R.dimen.input_title_line_height).value.sp,
            textAlign = TextAlign.Left,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Proportional,
                trim = LineHeightStyle.Trim.None
            ),
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        ),
        fontWeight = FontWeight.Bold
    )
}
