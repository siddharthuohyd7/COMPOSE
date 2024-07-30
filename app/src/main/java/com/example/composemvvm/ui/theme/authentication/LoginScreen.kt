package com.example.composemvvm.ui.theme.authentication


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Face5
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.example.composemvvm.ui.theme.datastore.IS_LOGGED_IN
import com.example.composemvvm.ui.theme.datastore.dataStore
import com.example.composemvvm.ui.theme.datastore.setValue
import com.example.composemvvm.ui.theme.defaultDarkBlue
import com.example.composemvvm.ui.theme.viewmodels.LoginViewModel
import com.example.composemvvm.ui.theme.widgets.ErrorTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun TextFieldError(error: String) {
    Text(
        error,
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp),
        style = TextStyle(color = Color.Red)
    )
}


@Composable
fun LoginScreen(viewModel: LoginViewModel, onRegisterCLick: () -> Unit, onLoginCLick: () -> Unit) {
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

    fun validateEmail(value: String):Boolean {
        emailValidationError.value = Validations.validateEmail(value)
        emailTextField.value = value
        return emailValidationError.value
    }

    fun validatePassword(value: String):Boolean {
        passwordValidationError.value = Validations.validatePassword(value)
        passwordTextField.value = value
        return passwordValidationError.value
    }

    val coroutineScope = rememberCoroutineScope()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = "login") {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.userStateFlow.collectLatest {
                if (it != null) {
                    context.dataStore.setValue(IS_LOGGED_IN, true)
                    onLoginCLick()
                } else {
                    Toast.makeText(context, "Kindly register the user", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {

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
        Spacer(modifier = Modifier.fillMaxHeight(0.025f))
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



        Spacer(modifier = Modifier.fillMaxHeight(0.08f))

        Button(
            onClick = {
                validatePassword(passwordTextField.value)
                validateEmail(emailTextField.value)
                if (!emailValidationError.value && !passwordValidationError.value) {
                    coroutineScope.launch {
                        viewModel.authenticate(emailTextField.value, passwordTextField.value)
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
                "Log In", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.fillMaxHeight(0.08f))

        Box(modifier = Modifier
            .height(75.dp)
            .width(75.dp)
            .background(defaultDarkBlue, shape = CircleShape)
            .wrapContentHeight(align = Alignment.CenterVertically)
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .clickable {
                onRegisterCLick()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowOutward,
                contentDescription = "Register",
                tint = Color.White, modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)


            )
        }


    }


}