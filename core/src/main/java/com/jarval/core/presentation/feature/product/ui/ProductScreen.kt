package com.jarval.core.presentation.feature.product.ui

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jarval.core.R
import com.jarval.core.presentation.feature.product.viewmodel.ProductSectionState
import com.jarval.core.presentation.feature.product.viewmodel.ProductUiEffect
import com.jarval.core.presentation.feature.product.viewmodel.ProductViewModel

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ProductUiEffect.LaunchToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        ProductSection(
            productState = state.value.productSectionState
        )
    }

}

@Composable
fun ProductSection(
    productState: ProductSectionState
) {
    when(productState){
        is ProductSectionState.Loading -> {
            CircularProgressIndicator()
        }
        is ProductSectionState.Success -> {
            SuccessProductSection(productState)
        }
        is ProductSectionState.Error -> {
            Text("An error occurred: ${productState.message}")
        }
    }
}

@Composable
fun SuccessProductSection(
    productState: ProductSectionState.Success,
){
    val listState = rememberLazyListState()
    //Show any button when scrolled object is > 3
    val showButton = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 3
        }
    }
    val productList = productState.productList

    LazyColumn(
        modifier = Modifier,
        state = listState,
        contentPadding = PaddingValues(
            vertical = 4.dp
        )
    ) {
        items(count = productList.size, key = { productList[it] }) { index ->
            Text("the product is: ${productList[index].name}")
        }
    }

    if (showButton.value) {
        Button(
            onClick = {}
        ) {
            Text(stringResource(R.string.product_go_top_button))
        }
    }
}