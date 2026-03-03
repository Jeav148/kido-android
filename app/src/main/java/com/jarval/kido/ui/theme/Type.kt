package com.jarval.kido.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import com.jarval.kido.R

// Set of Material typography styles to start with

val NunitoFamily = FontFamily(
    Font(R.font.nunito_semibold,   FontWeight.SemiBold),
    Font(R.font.nunito_bold,       FontWeight.Bold),
    Font(R.font.nunito_extrabold,  FontWeight.ExtraBold),
    Font(R.font.nunito_black,      FontWeight.Black),
)
val QuicksandFamily = FontFamily(
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_medium,  FontWeight.Medium),
    Font(R.font.quicksand_semibold,FontWeight.SemiBold),
    Font(R.font.quicksand_bold,    FontWeight.Bold),
)
val AppTypography = Typography(
    // Headings → Nunito
    displayLarge  = TextStyle(fontFamily = NunitoFamily, fontWeight = FontWeight.Black,     fontSize = 32.sp),
    displayMedium = TextStyle(fontFamily = NunitoFamily, fontWeight = FontWeight.ExtraBold, fontSize = 28.sp),
    headlineLarge = TextStyle(fontFamily = NunitoFamily, fontWeight = FontWeight.ExtraBold, fontSize = 24.sp),
    headlineMedium= TextStyle(fontFamily = NunitoFamily, fontWeight = FontWeight.Bold,      fontSize = 20.sp),
    headlineSmall = TextStyle(fontFamily = NunitoFamily, fontWeight = FontWeight.Bold,      fontSize = 18.sp),
    titleLarge    = TextStyle(fontFamily = NunitoFamily, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp),
    titleMedium   = TextStyle(fontFamily = NunitoFamily, fontWeight = FontWeight.Bold,      fontSize = 14.sp),
    // Body → Quicksand
    bodyLarge     = TextStyle(fontFamily = QuicksandFamily, fontWeight = FontWeight.Normal,   fontSize = 16.sp),
    bodyMedium    = TextStyle(fontFamily = QuicksandFamily, fontWeight = FontWeight.Normal,   fontSize = 14.sp),
    bodySmall     = TextStyle(fontFamily = QuicksandFamily, fontWeight = FontWeight.Normal,   fontSize = 12.sp),
    labelLarge    = TextStyle(fontFamily = QuicksandFamily, fontWeight = FontWeight.Bold,     fontSize = 14.sp),
    labelMedium   = TextStyle(fontFamily = QuicksandFamily, fontWeight = FontWeight.SemiBold, fontSize = 12.sp),
    labelSmall    = TextStyle(fontFamily = QuicksandFamily, fontWeight = FontWeight.SemiBold, fontSize = 10.sp),
)