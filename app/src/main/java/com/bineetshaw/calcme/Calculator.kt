package com.bineetshaw.calcme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private val calculatorButtons = listOf(
    "%", "÷", "×", "Back",
    "7", "8", "9", "-",
    "4", "5", "6", "+",
    "1", "2", "3", "/",
    "AC", "0", ".", "="
)

fun Modifier.innerShadow(
    color: Color = Color.Black.copy(alpha = 0.15f),
    cornersRadius: Dp = 0.dp,
    spread: Dp = 0.dp
) = drawWithContent {
    drawContent()

    val rect = Rect(Offset.Zero, size)
    val path = Path().apply {
        addRoundRect(
            androidx.compose.ui.geometry.RoundRect(
                rect,
                CornerRadius(cornersRadius.toPx())
            )
        )
    }

    clipPath(path) {
        drawRect(
            brush = Brush.verticalGradient(
                0f to color,
                spread.toPx() / size.height to Color.Transparent
            ),
            size = size
        )
        drawRect(
            brush = Brush.horizontalGradient(
                0f to color,
                spread.toPx() / size.width to Color.Transparent
            ),
            size = size
        )
    }
}

@Composable
fun Calculator(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val systemInDark = isSystemInDarkTheme()

    var isDark by remember { mutableStateOf(systemInDark) }
    var inputString by remember { mutableStateOf("") }
    var answerString by remember { mutableStateOf("") }

    LaunchedEffect(systemInDark) {
        isDark = systemInDark
    }

    LaunchedEffect(inputString) {
        if (inputString.isEmpty()) {
            answerString = ""
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(if (isDark) Color(0xFF17141C) else Color(0xFFEAEAFF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Switch(
                checked = isDark,
                onCheckedChange = { isDark = it },
                thumbContent = {
                    val iconId = if (isDark) R.drawable.ic_moon else R.drawable.ic_sun
                    Icon(
                        painter = painterResource(id = iconId),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (isDark) Color.White else Color(0xFFFFDF22)
                    )
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Black,
                    checkedTrackColor = Color(0xFFEAEAFF),
                    checkedBorderColor = Color(0xFFEAEAFF).copy(alpha = 0.5f),
                    checkedIconColor = Color.White,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color(0xFF1E1A24),
                    uncheckedBorderColor = Color(0xFF1E1A24).copy(alpha = 0.3f),
                    uncheckedIconColor = Color(0xFFFFDF22)
                )
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth(0.9f)
                .weight(0.8f)
                .clip(RoundedCornerShape(28.dp))
                .background(if (isDark) Color(0xFF1E1A24) else Color(0xFFEAEAFF))
                .innerShadow(
                    color = if (isDark) {
                        Color.Black.copy(alpha = 0.4f)
                    } else {
                        Color.Gray.copy(alpha = 0.2f)
                    },
                    cornersRadius = 28.dp,
                    spread = 12.dp
                ),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = inputString,
                    style = TextStyle(
                        fontSize = (screenWidth.value / 12).sp,
                        color = if (isDark) Color.White else Color(0xFF54537D)
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "=",
                        style = TextStyle(
                            fontSize = (screenWidth.value / 8).sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isDark) Color.White else Color(0xFF54537D)
                        )
                    )
                    Text(
                        answerString,
                        style = TextStyle(
                            fontSize = (screenWidth.value / 8).sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isDark) Color.White else Color(0xFF54537D)
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 25.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(calculatorButtons) { symbol ->
                CalculatorButton(
                    btn = symbol,
                    isDark = isDark,
                    onClick = { char ->
                        when (char) {
                            "AC" -> {
                                inputString = ""
                                answerString = ""
                            }

                            "Back" -> {
                                inputString = inputString.dropLast(1)
                            }

                            "=" -> if (inputString.isNotEmpty()) {
                                answerString = CalculatorLogic.calculate(inputString)
                            }

                            else -> inputString += char
                        }
                    },
                    onDelete = { inputString = inputString.dropLast(1) }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun CalculatorButton(
    btn: String,
    isDark: Boolean,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onDelete: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    LaunchedEffect(isPressed) {
        if (isPressed && btn == "Back") {
            while (isPressed) {
                onDelete()
                delay(100)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .shadow(
                elevation = if (isPressed) 2.dp else 6.dp,
                shape = RoundedCornerShape(33.dp),
                spotColor = if (isDark) Color.Black else Color(0xFF9E9EBE).copy(alpha = 0.6f),
                ambientColor = if (isDark) Color.Black else Color(0xFF9E9EBE).copy(alpha = 0.4f)
            )
            .drawBehind {
                if (!isPressed) {
                    val highlightColor = if (isDark) {
                        Color.White.copy(alpha = 0.08f)
                    } else {
                        Color.White.copy(alpha = 1f)
                    }

                    drawRoundRect(
                        color = highlightColor,
                        topLeft = Offset(-4f, -4f),
                        size = size,
                        cornerRadius = CornerRadius(33.dp.toPx())
                    )
                }
            }
            .clip(RoundedCornerShape(33.dp))
            .pointerInput(btn) {
                detectTapGestures(
                    onTap = { onClick(btn) },
                    onLongPress = { if (btn == "Back") isPressed = true },
                    onPress = {
                        try {
                            awaitRelease()
                        } finally {
                            isPressed = false
                        }
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(33.dp),
            color = buttonColor(btn, isDark)
        ) {
            Box(contentAlignment = Alignment.Center) {
                if (btn == "Back") {
                    Image(
                        imageVector = Icons.AutoMirrored.Outlined.Backspace,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        colorFilter = ColorFilter.tint(
                            if (isDark) Color(0xFFF9C7C8) else Color(0xFFE37369)
                        )
                    )
                } else {
                    Text(
                        text = btn,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (btn == "=" || isDark) Color.White else Color(0xFF54537D)
                    )
                }
            }
        }
    }
}

private fun buttonColor(btn: String, isDark: Boolean): Color {
    return if (isDark) {
        when (btn) {
            "=" -> Color(0xFF4E4271)
            "Back" -> Color(0xFFF5747A)
            "%", "÷", "×", "-", "+", "/" -> Color(0xFF1D1A26)
            else -> Color(0xFF17141C)
        }
    } else {
        when (btn) {
            "=" -> Color(0xFF515282)
            "Back" -> Color(0xFFF9C7C8)
            "%", "÷", "×", "-", "+", "/" -> Color(0xFFDAD9F8)
            else -> Color(0xFFE7E8FC)
        }
    }
}
