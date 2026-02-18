package com.jarval.kido.domain.model.feature.dashboard

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector

@Stable
data class CategoryItem (
    val icon: Int,
    val title: String,
    val subtitle: String
)