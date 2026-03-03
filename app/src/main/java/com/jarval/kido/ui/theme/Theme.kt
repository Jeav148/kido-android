package com.jarval.kido.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    // Primary — eco green
    primary            = EcoGreenDark,       // #4CAF50
    onPrimary          = Color.White,
    primaryContainer   = MintLight,          // #D4F5E4
    onPrimaryContainer = TextDark,           // #3D3D3D
    // Secondary — blush
    secondary          = BlushDark,          // #FF8FA5
    onSecondary        = Color.White,
    secondaryContainer = BlushLight,         // #FFE0E8
    onSecondaryContainer = TextDark,
    // Tertiary — lavender
    tertiary           = LavenderDark,       // #A78BBF
    onTertiary         = Color.White,
    tertiaryContainer  = LavenderLight,      // #E8DFF0
    onTertiaryContainer = TextDark,
    // Background & surfaces
    background         = WarmWhite,          // #FFF9F5
    onBackground       = TextDark,           // #3D3D3D
    surface            = Color.White,
    onSurface          = TextDark,
    surfaceVariant     = WarmCream,          // #FFF3EB
    onSurfaceVariant   = TextMid,            // #6B6B6B
    // Outline
    outline            = InactiveGray,       // #B0B0B0
    outlineVariant     = Color(0xFFE0E0E0),
    // Error
    error              = Color(0xFF93000A),
    onError            = Color.White,
    errorContainer     = Color(0xFFFFDAD6),
    onErrorContainer   = Color(0xFF410002),
)
// ── Dark Color Scheme ──────────────────────────────────────────
private val DarkColorScheme = darkColorScheme(
    // Primary — eco green (brighter for dark bg)
    primary            = EcoGreen,           // #6BCB77
    onPrimary          = Color(0xFF003910),
    primaryContainer   = EcoGreenDark,       // #4CAF50
    onPrimaryContainer = MintLight,          // #D4F5E4
    // Secondary — blush
    secondary          = Blush,              // #FFB7C5
    onSecondary        = Color(0xFF5C1128),
    secondaryContainer = Color(0xFF7B2D3E),
    onSecondaryContainer = BlushLight,       // #FFE0E8
    // Tertiary — lavender
    tertiary           = Lavender,           // #C3AED6
    onTertiary         = Color(0xFF3A1F52),
    tertiaryContainer  = Color(0xFF52336B),
    onTertiaryContainer = LavenderLight,     // #E8DFF0
    // Background & surfaces
    background         = DarkBackground,     // #1A1F1C
    onBackground       = DarkTextPrimary,    // #F0EDE8
    surface            = DarkSurface,        // #242B26
    onSurface          = DarkTextPrimary,
    surfaceVariant     = DarkSurfaceAlt,     // #2E3830
    onSurfaceVariant   = DarkTextSecond,     // #B8B3AC
    // Outline
    outline            = DarkTextTertiary,   // #7A756E
    outlineVariant     = Color(0xFF3D4840),
    // Error
    error              = Color(0xFFFFB4AB),
    onError            = Color(0xFF690005),
    errorContainer     = Color(0xFF93000A),
    onErrorContainer   = Color(0xFFFFDAD6),
)

@Composable
fun KidoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+. Default disabled so the app uses
    // the app-defined color schemes unless the caller explicitly opts in.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}