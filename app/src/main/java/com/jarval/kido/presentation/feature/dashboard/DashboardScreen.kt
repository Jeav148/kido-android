package com.jarval.kido.presentation.feature.dashboard

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import com.jarval.kido.R
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column() {
            Greeting(modifier = modifier.padding(top = 4.dp))
            OfferCard()
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

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column() {
            Text(
                text = "Good morning",
                fontSize = 14.sp
            )
            Text(
                text = "Hey, Mama!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Card(
            modifier = Modifier.size(48.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notification"
                )
            }
        }
    }

}

@Composable
fun OfferCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "ECO SPECIAL",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                fontFamily = MaterialTheme.typography.headlineSmall.fontFamily
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(bottom = 12.dp),
                text = stringResource(R.string.offer_banner_hero),
                fontSize = 18.sp,
                fontWeight = FontWeight.Black
            )

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface

                ),
                onClick = {}
            ) {
                Text(
                    text = stringResource(R.string.shop_now),
                    color = MaterialTheme.colorScheme.primary
                )
            }
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
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categoryItems.size) { item ->
            val category = categoryItems[item]
            CategoriesItemCard(
                iconResource = Icons.Default.Face,
                title = category.title,
                subtitle = category.subtitle,
                index = item % 4
            )
        }
    }
}

@Composable
fun CategoriesItemCard(
    iconResource: ImageVector,
    title: String,
    subtitle: String,
    index: Int = 0
) {
    val backgroundColor: Color = when (index) {
        0 -> MaterialTheme.colorScheme.primaryContainer
        1 -> MaterialTheme.colorScheme.secondaryContainer
        2 -> MaterialTheme.colorScheme.tertiaryContainer
        3 -> MaterialTheme.colorScheme.surfaceVariant
        else -> MaterialTheme.colorScheme.surface
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .padding(12.dp),
                imageVector = iconResource,
                contentDescription = ""
            )
            Text(
                modifier = Modifier.padding(bottom = 6.dp),
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = subtitle,
                fontSize = 8.sp,
                fontWeight = FontWeight.Light
            )
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
