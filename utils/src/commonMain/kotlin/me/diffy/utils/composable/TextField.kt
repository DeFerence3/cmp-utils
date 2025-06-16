package me.diffy.utils.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import me.diffy.utils.InputFieldBackgroundColor
import me.diffy.utils.InputFieldCornerRadius
import me.diffy.utils.InputFieldElevation
import me.diffy.utils.InputFieldFontSize
import me.diffy.utils.InputFieldHorizontalPadding
import me.diffy.utils.InputFieldPlaceholderColor
import me.diffy.utils.InputFieldTextColor
import me.diffy.utils.InputFieldVerticalPadding
import me.diffy.utils.applyWhen

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String?,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardType: KeyboardType =  KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    readOnly: Boolean = false,
    trailingIcon: (@Composable () -> Unit)? = null
){

    Surface(
        modifier = modifier
            .height(OutlinedTextFieldDefaults.MinHeight)
            .shadow(elevation = InputFieldElevation, shape = RoundedCornerShape(InputFieldCornerRadius)),
        shape = RoundedCornerShape(InputFieldCornerRadius),
        color = InputFieldBackgroundColor,
    ) {
        Box(
            modifier = Modifier
                .padding(
                    horizontal = InputFieldHorizontalPadding,
                    vertical = InputFieldVerticalPadding
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            if (value.isEmpty() && placeholder != null) {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        color = InputFieldPlaceholderColor,
                        fontSize = InputFieldFontSize
                    )
                )
            }

            BasicTextField(
                modifier = Modifier.Companion
                    .applyWhen(focusRequester != null){
                        focusRequester(focusRequester!!)
                    },
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(
                    color = InputFieldTextColor,
                    fontSize = InputFieldFontSize
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = imeAction,
                    keyboardType = keyboardType
                ),
                readOnly = readOnly,
                enabled = !readOnly,
                keyboardActions = keyboardActions,
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        innerTextField()
                        trailingIcon?.invoke()
                    }
                }
            )
        }
    }
}