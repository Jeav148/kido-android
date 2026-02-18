package com.jarval.kido.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SectionHeader(
    title: String,
    actionLabel: String,
    onClick: () -> Unit
){
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = title)
        TextButton(
            onClick = onClick,
            modifier = Modifier
        ) {
            Text(
                text = actionLabel,
                color = Color.Blue
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = ""
            )
        }
    }
}