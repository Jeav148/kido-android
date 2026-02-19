package com.jarval.kido.presentation.feature.dashboard

sealed class DashboardUiEffect {

    data class ShowError(val message: String) : DashboardUiEffect()
    data object NavigateToCategory : DashboardUiEffect()

}