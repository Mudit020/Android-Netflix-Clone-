package com.mudit20.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    val route: String

    @Serializable
    data object OnBoarding : Route {
        override val route = "onboarding"
    }

    @Serializable
    data object AuthScreen : Route {
        override val route = "auth_screen"
    }

    @Serializable
    data object DashBoard : Route {
        override val route = "dashboard"
    }
}
