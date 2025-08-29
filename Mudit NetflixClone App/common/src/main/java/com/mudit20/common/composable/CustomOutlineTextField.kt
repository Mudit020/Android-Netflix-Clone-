package com.mudit20.common.composable

import android.graphics.drawable.Drawable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CustomOutlineeTextField(onInputChanged: (String) -> Unit,text:String="",
                            hintText:String="",placeHolderText:String="",borderColors: Color= Color.Gray,borderWidth: Dp=1.dp,
                            shape: Shape= RectangleShape,
                            modifier: Modifier= Modifier,
                            isSingleLine:Boolean=true,
                            nextFocus: Boolean=false,
                            rightDrawable: Int?=null,
                            leftDrawable: Int?=null,
                            isPasswordField: Boolean=false,
                            isEmailField: Boolean=false,
                            isErrMessage: String="",
                            isError: Boolean=false)
{
    val textFieldValue= remember{
        mutableStateOf("")
    }
    val isPasswordVisible= remember{
        mutableStateOf(false)
    }
    val error by remember {
        mutableStateOf(isErrMessage)
    }


    OutlinedTextField(
        singleLine = isSingleLine,
        modifier = modifier.padding(bottom = 20.dp),
        value = textFieldValue.value,
        onValueChange = {
            if (isPasswordField) {
                if (isValidPassword(it)) {
                    onInputChanged(it)
                    textFieldValue.value=it
                }
            }
            if (isEmailField) {
                if (isValidEmail(it)) {
                    onInputChanged(it)
                    textFieldValue.value = it
                }
            }
            onInputChanged(it)
            textFieldValue.value = it
        },
        placeholder = {
            Text(text = placeHolderText)
        },
        label = {
            Text(text = hintText, style = MaterialTheme.typography.bodyMedium)
        },
        visualTransformation = if (isPasswordField) if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = borderColors,
            unfocusedIndicatorColor = borderColors,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPasswordField) KeyboardType.Password else if (isEmailField) KeyboardType.Email else KeyboardType.Text,
            imeAction = if (nextFocus) ImeAction.Next else ImeAction.Done
        ),
        trailingIcon = {
            if(isPasswordField) {


                if (textFieldValue.value.isNotEmpty()) {
                    val icon =
                        if (isPasswordVisible.value) Icons.Rounded.FavoriteBorder else Icons.Rounded.FavoriteBorder // Consider using different icons for visible/hidden
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            isPasswordVisible.value = !isPasswordVisible.value
                        })
                }
            }
        },
        shape = shape,
        supportingText = {
            if (isError) {
                Text(text = error, color = MaterialTheme.colorScheme.error)
            }
        }
    )
}
fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
fun isValidPassword(password: String): Boolean {
    return password.length >= 6
}



