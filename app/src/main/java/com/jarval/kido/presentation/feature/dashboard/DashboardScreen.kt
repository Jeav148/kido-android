package com.jarval.kido.presentation.feature.dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jarval.kido.domain.model.feature.dashboard.CategoryItem
import com.jarval.kido.domain.model.feature.dashboard.ProductItem
import com.jarval.kido.presentation.components.SectionHeader

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is DashboardUiEffect.ShowError -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }

        }
    }

    when {
        else -> {
            Column(modifier = modifier) {
                Greeting(modifier = modifier)
                OfferCard(modifier = modifier)
                CategoriesState(
                    modifier = modifier,
                    state = state.value,
                    headerActionClick = viewModel::navigateToCategory
                )
                PopularProductsState(
                    modifier = modifier,
                    state = state.value
                )
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Row {
                Text(
                    text = "Good morning"
                )
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
            Row {
                Text(
                    text = "Hey, Mama!"
                )
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = ""
                )
            }
        }

        IconButton(
            onClick = { }
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = ""
            )
        }
    }

}

@Composable
fun OfferCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "ECO SPECIAL"
        )
        Text(
            text = "20% Off Your First Green Bundle"
        )

        Button(
            onClick = {}
        ) {
            Text(
                text = "Shop Now"
            )
        }
    }
}

@Composable
fun CategoriesState(
    modifier: Modifier = Modifier,
    state: DashboardUiState,
    headerActionClick: (String) -> Unit
) {
    when (state.categoryState) {
        is CategoryState.Loading -> {
            CircularProgressIndicator(modifier)
        }

        is CategoryState.Success -> {
            CategoriesSuccess(
                categoryItems = state.categoryState.categories,
                onHeaderActionClick = headerActionClick
            )
        }

        is CategoryState.Error -> {
            Text("An error occurred: ${(state.categoryState).message}")
        }
    }

}

@Composable
fun CategoriesSuccess(
    categoryItems: List<CategoryItem>,
    onHeaderActionClick: (String) -> Unit,
) {
    Column() {
        SectionHeader(
            title = "Categories",
            actionLabel = "See all",
            onClick = onHeaderActionClick
        )
        CategoriesGrid(
            categoryItems = categoryItems
        )
    }
}

@Composable
fun CategoriesGrid(
    categoryItems: List<CategoryItem>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categoryItems.size) { item ->
            val category = categoryItems[item]
            CategoriesItemCard(
                iconResource = Icons.Default.Face,
                title = category.title,
                subtitle = category.subtitle
            )
        }
    }
}

@Composable
fun CategoriesItemCard(
    iconResource: ImageVector,
    title: String,
    subtitle: String
) {
    Card {
        Column(modifier = Modifier.padding(4.dp)) {
            Icon(
                imageVector = iconResource,
                contentDescription = ""
            )
            Text(title)
            Text(subtitle)
        }
    }
}

@Composable
fun PopularProductsState(
    modifier: Modifier = Modifier,
    state: DashboardUiState
) {
    when (state.productState) {
        is ProductState.Loading -> {
            CircularProgressIndicator(modifier)
        }

        is ProductState.Success -> {
            PopularProductsSuccess(
                popularProducts = state.productState.products
            )
        }

        is ProductState.Error -> {
            Text("An error occurred: ${(state.productState).message}")
        }
    }
}

@Composable
fun PopularProductsSuccess(popularProducts: List<ProductItem>) {
    val lazyListState = rememberLazyListState()
    val limit = 5
    val showButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0
        }
    }

    SectionHeader(
        title = "Popular Products",
        actionLabel = "See all",
        onClick = {}
    )

    LazyRow(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        state = lazyListState
    ) {
        items(count = popularProducts.size, key = { index -> popularProducts[index].id }) { index ->
            val item = popularProducts[index]
            PopularItemCard(
                title = item.title,
                description = item.amount,
                price = item.price,
                favorite = item.favorite
            )
        }
    }
}

@Composable
fun PopularItemCard(
    title: String,
    description: String,
    price: String,
    favorite: Boolean
) {
    Card() {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            ) {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = ""
                )
            }
            Text(title)
            Text(description)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(price)
                if (favorite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = ""
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}


