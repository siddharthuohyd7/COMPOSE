package com.example.composemvvm.ui.theme.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.composemvvm.ui.theme.authentication.TextFieldError
import com.example.composemvvm.ui.theme.defaultDarkBlue

@Composable
fun ErrorTextField(
    keyboardType: KeyboardType,
    validate: (value: String) -> Boolean,
    labelText: String,
    placeHolderText :String,
    leadingIcon: ImageVector,
    isPasswordField: Boolean,
    errorText :String,
    initialText :String,
    isErrorText:Boolean

) {
    val textFieldValue = remember {
        mutableStateOf(initialText)
    }

    val textFieldError = remember {
        mutableStateOf(isErrorText)
    }

    val passwordViewState = remember {
        mutableStateOf(false)
    }

    Column {
        OutlinedTextField(
            isError = textFieldError.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (!passwordViewState.value && isPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
            label = { Text(text = labelText) },
            placeholder = { Text(placeHolderText) },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = defaultDarkBlue,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "Star"
                )
            },
            trailingIcon = {
                if (isPasswordField)
                    Icon(
                        imageVector = if (passwordViewState.value)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff,
                        contentDescription = "Favorite",
                        modifier = Modifier.clickable {
                            passwordViewState.value = !passwordViewState.value
                        }

                    )
            },
            value = textFieldValue.value, onValueChange = {

               val isValue = validate(it)
                textFieldError.value =isValue
                textFieldValue.value = it
            })
        if (textFieldError.value)
            TextFieldError(errorText)

    }
}