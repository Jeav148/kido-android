package com.jarval.kido.presentation.feature.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jarval.kido.domain.model.feature.dashboard.CategoryItem
import com.jarval.kido.domain.repository.DemoData
import com.jarval.kido.presentation.feature.dashboard.CategoriesGrid

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel()
){
    val state = viewModel.state.collectAsStateWithLifecycle()

    Column (
        modifier = Modifier.padding(4.dp)
    ) {
        Text("List of all categories")
        Spacer(modifier = Modifier.height(8.dp))
        CategoriesGrid(
            categoryItems = state.value.categories
        )
    }
}

@Preview
@Composable
fun CategoryScreenPreview(){
    CategoryScreen()
}