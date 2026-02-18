package com.jarval.kido.presentation.feature.dashboard

sealed class DashboardIntent {

    data object LoadCategories : DashboardIntent()
    data object LoadProducts : DashboardIntent()

}