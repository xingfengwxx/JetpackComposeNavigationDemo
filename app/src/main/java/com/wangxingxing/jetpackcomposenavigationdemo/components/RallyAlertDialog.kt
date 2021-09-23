package com.wangxingxing.jetpackcomposenavigationdemo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.wangxingxing.jetpackcomposenavigationdemo.ui.theme.RallyDialogThemeOverlay

/**
 * author : 王星星
 * date : 2021/9/23 10:04
 * email : 1099420259@qq.com
 * description :
 */
@Composable
fun RallyAlertDialog(
    onDismiss: () -> Unit,
    bodyText: String,
    buttonText: String
) {
    RallyDialogThemeOverlay {
        AlertDialog(
            onDismissRequest = onDismiss,
            text = { Text(bodyText) },
            buttons = {
                Column {
                    Divider(
                        Modifier.padding(horizontal = 12.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                    )
                    TextButton(
                        onClick = onDismiss,
                        shape = RectangleShape,
                        contentPadding = PaddingValues(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(buttonText)
                    }
                }
            }
        )
    }
}