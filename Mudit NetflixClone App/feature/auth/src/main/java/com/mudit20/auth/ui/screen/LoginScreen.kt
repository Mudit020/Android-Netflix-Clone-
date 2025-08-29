package com.mudit20.auth.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mudit20.a35netflixclone.ui.theme.NetflixRed
import com.mudit20.auth.domain.model.request.LoginDomainRequest
import com.mudit20.auth.ui.state.LoginIntent
import com.mudit20.auth.ui.state.LoginState
import com.mudit20.auth.ui.viewmodel.LoginViewModel
import com.mudit20.common.composable.CustomButton
import com.mudit20.common.composable.CustomOutlineeTextField

@Composable
fun LoginScreen(onSuccessfulLogin: () -> Unit) {
    val loginViewModel = hiltViewModel<LoginViewModel>()
    val state by loginViewModel.state.collectAsState()
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(false) }
    val isButtonEnabled by remember {
        derivedStateOf { !isEmailValid && !isPasswordValid && email.isNotEmpty() && password.isNotEmpty() }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
                .verticalScroll(scrollState),
        ) {
            Icon(
                modifier = Modifier
                    .padding(vertical = 15.dp),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Welcome Back",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(150.dp))

            CustomOutlineeTextField(
                onInputChanged = { email = it },
                hintText = "Email",
                placeHolderText = "Enter your Email",
                modifier = Modifier.fillMaxWidth(),
                isSingleLine = true,
                nextFocus = true,
                isEmailField = true,
                isError = isEmailValid
            )

            CustomOutlineeTextField(
                onInputChanged = { password = it },
                hintText = "Password",
                placeHolderText = "Enter your Password",
                modifier = Modifier.fillMaxWidth(),
                isSingleLine = true,
                nextFocus = false,
                isPasswordField = true,
                isError = isPasswordValid
            )

            Text(
                text = "Forgot Password?",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (email.isNotBlank() && password.isNotBlank()) {
                        loginViewModel.intent.trySend(
                            LoginIntent.Login(LoginDomainRequest(email, password))
                        )
                    } else {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                },
                text = "LOGIN",
                isEnabled = isButtonEnabled
            )
        }
        HandleLoginState(state, context, onSuccessfulLogin)
    }

    if (state is LoginState.Loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun HandleLoginState(state: LoginState, context: Context, onSuccessfulLogin: () -> Unit) {
    when (val currentState = state) {
        is LoginState.Error -> {
            LaunchedEffect(currentState) {
                Toast.makeText(context, currentState.error, Toast.LENGTH_SHORT).show()
            }
        }
        is LoginState.Success -> {
            onSuccessfulLogin()
            LaunchedEffect(currentState) {
                Toast.makeText(context, currentState.message, Toast.LENGTH_SHORT).show()
            }
        }
        else -> {} // No action for Idle or Loading here
    }
} 