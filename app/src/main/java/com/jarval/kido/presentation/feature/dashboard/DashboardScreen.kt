package com.jarval.kido.presentation.feature.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jarval.kido.domain.model.feature.dashboard.CategoryItem
import com.jarval.kido.domain.model.feature.dashboard.PopularItem
import com.jarval.kido.presentation.components.SectionHeader

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Greeting(modifier = modifier)
        OfferCard(modifier = modifier)
        Categories(modifier = modifier)
        PopularProducts(modifier = modifier)
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
fun Categories(modifier: Modifier = Modifier) {
    val categoryItems = listOf(
        CategoryItem(Icons.Default.Build, "Baby Diapers", "12 products"),
        CategoryItem(Icons.Default.Add, "Makeup Removers", "8 products"),
        CategoryItem(Icons.Default.Face, "Period Pads", "15 products"),
        CategoryItem(Icons.Default.ArrowForward, "Lactation Pads", "6 products")
    )

    Column() {
        SectionHeader(
            title = "Categories",
            actionLabel = "See all"
        )
        CategoriesGrid(categoryItems)
    }
}

@Composable
fun CategoriesGrid(categoryItems: List<CategoryItem>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categoryItems.size) { item ->
            val category = categoryItems[item]
            CategoriesItemCard(
                iconResource = category.icon,
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
    Card() {
        Column() {
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
fun PopularProducts(modifier: Modifier = Modifier) {
    val popularProducts = listOf(
        PopularItem("Baby Diapers", "12 products", "$12.50", favorite = true),
        PopularItem("Bamboo Diapers and Pads", "15 products", "$12.50", favorite = false)
    )
    SectionHeader(
        title = "Popular Products",
        actionLabel = "See all"
    )

    LazyRow (
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {
        items(popularProducts.size) { index ->
            val item = popularProducts[index]
            PopularItemCard(
                title = item.title,
                description = item.description,
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
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
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


