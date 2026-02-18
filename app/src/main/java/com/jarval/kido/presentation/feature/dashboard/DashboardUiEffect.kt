package com.jarval.kido.presentation.feature.dashboard

sealed class DashboardUiEffect {

    data class ShowErrorToast(val message: String) : DashboardUiEffect()
    data object NavigateToCategory : DashboardUiEffect()

}