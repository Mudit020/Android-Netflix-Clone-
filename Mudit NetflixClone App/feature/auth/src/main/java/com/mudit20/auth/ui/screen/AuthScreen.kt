package com.mudit20.auth.screen

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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mudit20.a35netflixclone.ui.theme.NetflixRed
import com.mudit20.auth.domain.model.request.SignUpDomainRequest
import com.mudit20.auth.screen.HandleAuthState
import com.mudit20.auth.ui.state.AuthIntent
import com.mudit20.auth.ui.state.AuthState
import com.mudit20.auth.ui.viewmodel.AuthViewModel
import com.mudit20.common.composable.CustomButton
import com.mudit20.common.composable.CustomOutlineeTextField

@Composable
fun AuthScreen(onSuccesfullLogin: () -> Unit) {
    val authViewModel = hiltViewModel<AuthViewModel>()
    val state by authViewModel.state.collectAsState()
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val isRegisterScreen = remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
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
                    .padding(vertical = 15.dp)
                    .clickable {
                        isRegisterScreen.value = !isRegisterScreen.value
                    },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = if (!isRegisterScreen.value) "Welcome Back" else "Sign Up",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (isRegisterScreen.value) {
                Text(
                    "Let's SignUp Your Account",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(150.dp))

            if (isRegisterScreen.value) {
                CustomOutlineeTextField(
                    onInputChanged = { name = it },
                    hintText = "Name",
                    placeHolderText = "Enter your name",
                    modifier = Modifier.fillMaxWidth(),
                    isSingleLine = true,
                    nextFocus = true,
                    isEmailField = true,
                    isError = false
                )
            }

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

            if (!isRegisterScreen.value) {
                Text(
                    text = "Forgot Password?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (email.isNotBlank() && password.isNotBlank() && (!isRegisterScreen.value || name.isNotBlank())) {
                        if (isRegisterScreen.value) {
                            authViewModel.intent.trySend(
                                AuthIntent.SignUp(SignUpDomainRequest(name, email, password))
                            )
                        } else {
                            authViewModel.intent.trySend(
                                AuthIntent.Login(email, password)
                            )
                        }
                    } else {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                },
                text = if (isRegisterScreen.value) "SIGN UP" else "LOGIN",
                isEnabled = isButtonEnabled
            )

            val annotatedString = buildAnnotatedString {
                append(if (isRegisterScreen.value) "Already have an account? " else "Don't have an account? ")
                withStyle(style = SpanStyle(NetflixRed)) {
                    append(if (isRegisterScreen.value) "Login" else "Sign Up")
                }
            }

            Text(
                annotatedString,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isRegisterScreen.value = !isRegisterScreen.value
                    },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        HandleAuthState(state, context, onSuccesfullLogin, isRegisterScreen)
    }

    if (state is AuthState.Loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun HandleAuthState(
    state: AuthState,
    context: Context,
    onSuccesfullLogin: () -> Unit,
    isRegisterScreen: MutableState<Boolean>
) {
    when (val currentState = state) {
        is AuthState.Error -> {
            LaunchedEffect(currentState) {
                Toast.makeText(context, currentState.error, Toast.LENGTH_SHORT).show()
            }
        }
        is AuthState.SignUpSuccess -> {
            isRegisterScreen.value = false
            LaunchedEffect(currentState) {
                Toast.makeText(context, currentState.message, Toast.LENGTH_SHORT).show()
            }
        }
        is AuthState.LoginSuccess -> {
            onSuccesfullLogin()
            LaunchedEffect(currentState) {
                Toast.makeText(context, currentState.message, Toast.LENGTH_SHORT).show()
            }
        }
        else -> {} // No action for Idle or Loading here
    }
}


