package com.jarval.kido.presentation.feature.dashboard

sealed class DashboardEffect {

    data class ShowErrorToast(val message: String) : DashboardEffect()

}