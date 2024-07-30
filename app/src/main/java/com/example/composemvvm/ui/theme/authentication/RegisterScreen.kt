package com.example.composemvvm.ui.theme.authentication

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face5
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.composemvvm.Validations
import com.example.composemvvm.ui.theme.defaultDarkBlue
import com.example.composemvvm.ui.theme.room.User
import com.example.composemvvm.ui.theme.viewmodels.RegisterViewModel
import com.example.composemvvm.ui.theme.widgets.ErrorTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

@Composable
fun RegisterScreen(viewModel: RegisterViewModel, onRegisterClick: () -> Unit) {
    val context = LocalContext.current.applicationContext

    val emailTextField = remember {
        mutableStateOf("")
    }

    val passwordTextField = remember {
        mutableStateOf("")
    }

    val emailValidationError = remember {
        mutableStateOf(false)
    }

    val passwordValidationError = remember {
        mutableStateOf(false)
    }

    val firstNameTextField = remember {
        mutableStateOf("")
    }

    val lastNameTextField = remember {
        mutableStateOf("")
    }

    val firstNameValidationError = remember {
        mutableStateOf(false)
    }

    val lastNameValidationError = remember {
        mutableStateOf(false)
    }

    val passwordViewState = remember {
        mutableStateOf(false)
    }

    fun validateFirstName(value: String): Boolean {
        firstNameValidationError.value = Validations.validateName(value)
        firstNameTextField.value = value
        return firstNameValidationError.value
    }

    fun validateLastName(value: String): Boolean {
        lastNameValidationError.value = Validations.validateName(value)
        lastNameTextField.value = value
        return lastNameValidationError.value
    }

    fun validateEmail(value: String): Boolean {
        emailValidationError.value = Validations.validateEmail(value)
        emailTextField.value = value
        return emailValidationError.value
    }

    fun validatePassword(value: String): Boolean {
        passwordValidationError.value = Validations.validatePassword(value)
        passwordTextField.value = value
        return passwordValidationError.value
    }

    val coroutineScope = rememberCoroutineScope()
    val lifecycle = LocalLifecycleOwner.current.lifecycle


    LaunchedEffect(key1 = "register") {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.userStateFlow.collectLatest {
                if (it == "Error") {
                    Toast.makeText(context, "User already exists in system", Toast.LENGTH_SHORT).show()
                } else {
                    onRegisterClick()
                }
            }
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {

        ErrorTextField(
            keyboardType = KeyboardType.Text,
            validate = {
                validateFirstName(it)
            },
            errorText = "Please enter first name",
            labelText = "First Name",
            placeHolderText = "Enter first name",
            leadingIcon = Icons.Default.Face5,
            isPasswordField = false,
            initialText = firstNameTextField.value,
            isErrorText = firstNameValidationError.value

        )

        Spacer(modifier = Modifier.height(20.dp))

        ErrorTextField(
            keyboardType = KeyboardType.Text,
            validate = {
                validateLastName(it)
            },
            errorText = "Please enter last name",
            labelText = "Last Name",
            placeHolderText = "Enter last name",
            leadingIcon = Icons.Default.Face5,
            isPasswordField = false,
            initialText = firstNameTextField.value,
            isErrorText = firstNameValidationError.value

        )
        Spacer(modifier = Modifier.height(20.dp))
        ErrorTextField(
            keyboardType = KeyboardType.Email,
            validate = {
                validateEmail(it)
            },
            errorText = "Please enter an email",
            labelText = "Email",
            placeHolderText = "Enter email",
            leadingIcon = Icons.Default.Face5,
            isPasswordField = false,
            initialText = emailTextField.value,
            isErrorText = emailValidationError.value

        )
        Spacer(modifier = Modifier.height(20.dp))
        ErrorTextField(
            keyboardType = KeyboardType.Password,
            validate = {
                validatePassword(it)
            },
            errorText = "Please enter a password",
            labelText = "Password",
            placeHolderText = "Enter password",
            leadingIcon = Icons.Default.Password,
            isPasswordField = true,
            initialText = passwordTextField.value,
            isErrorText = passwordValidationError.value

        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                validatePassword(passwordTextField.value)
                validateEmail(emailTextField.value)
                validateFirstName(firstNameTextField.value)
                validateLastName(lastNameTextField.value)
                if (!emailValidationError.value && !passwordValidationError.value && !firstNameValidationError.value && !lastNameValidationError.value) {
                    coroutineScope.launch {
                        try {
                            viewModel.storeUser(
                                User(
                                    firstname = firstNameTextField.value,
                                    lastname = lastNameTextField.value,
                                    email = emailTextField.value,
                                    password = passwordTextField.value,
                                    createdAt = Date(Calendar.getInstance().timeInMillis),
                                )
                            )
                        } catch (e: Exception) {
                            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                        }


                    }

                }

            },
            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 5.dp),
            colors = ButtonDefaults.buttonColors()
                .copy(containerColor = defaultDarkBlue, contentColor = Color.White),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth(0.60f)
                .height(60.dp)
        ) {
            Text(
                "Register", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }

}