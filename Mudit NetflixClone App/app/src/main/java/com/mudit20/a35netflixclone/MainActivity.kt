package com.mudit20.a35netflixclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mudit20.a35netflixclone.ui.theme._35NetflixCloneTheme
import com.mudit20.a35netflixclone.viewmodel.SessionViewModel
import com.mudit20.core.navigation.Route
import com.mudit20.onboarding.screen.OnBoardScreen
import com.mudit20.auth.screen.AuthScreen
import com.mudit20.netflixclone.feature.dashboard.screen.ui.DashBoardSCreen
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SessionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _35NetflixCloneTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val isLoggedIn = viewModel.isLoggedIn.collectAsState()
                    val navcontroller = rememberNavController()



                            NavHost(navController = navcontroller, startDestination =if(isLoggedIn.value) Route.DashBoard.route else Route.OnBoarding.route,
                                modifier = Modifier.padding(innerPadding)) {
                                composable(Route.OnBoarding.route) {
    OnBoardScreen(navigation = {

        navcontroller.navigate(Route.AuthScreen.route)
    }, modifier = Modifier.padding(innerPadding))
}
                                composable(Route.AuthScreen.route) {
                                    AuthScreen(
                                        onSuccesfullLogin = {
                                            viewModel.setLoginStatus(true)
                                            navcontroller.navigate(Route.DashBoard.route)
                                        }
                                    )
                                }
                                composable(Route.DashBoard.route) {
                                    DashBoardSCreen()
                                }
                            }

                }
            }
        }
    }
}


