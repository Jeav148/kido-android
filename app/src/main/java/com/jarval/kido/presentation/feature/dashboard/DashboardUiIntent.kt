package com.jarval.kido.presentation.feature.dashboard

sealed class DashboardUiIntent {

    data object LoadAllData : DashboardUiIntent()

    data object LoadCategories : DashboardUiIntent()
    data object OpenCategories : DashboardUiIntent()

    data class LoadPopularProducts(val limit: Int): DashboardUiIntent()

}