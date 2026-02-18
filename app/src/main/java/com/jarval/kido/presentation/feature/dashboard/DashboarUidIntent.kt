package com.jarval.kido.presentation.feature.dashboard

sealed class DashboarUidIntent {

    data object LoadCategories : DashboarUidIntent()
    data object OpenCategories : DashboarUidIntent()

}