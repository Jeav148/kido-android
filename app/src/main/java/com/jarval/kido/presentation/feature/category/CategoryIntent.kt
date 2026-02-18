package com.jarval.kido.presentation.feature.category

sealed class CategoryIntent {

    data object LoadCategories : CategoryIntent()

}
