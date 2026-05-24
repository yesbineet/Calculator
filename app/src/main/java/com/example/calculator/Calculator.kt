package com.example.calculator

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.graphics.BlurMaskFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun buttonList() = listOf(
    "%","÷","*","Back",
    "7","8","9","-",
    "4","5","6","+",
    "1","2","3","/",
    "AC","0",".","="
)

@Composable
fun Calculator(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(200.dp)
                // 1. Apply the shadow FIRST
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(24.dp),
                    ambientColor = Color.Black
                )
                .clip(RoundedCornerShape(24.dp))
                .background(color = Color(0xFFF3EBF8)),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "123+123",
                    style = TextStyle(fontSize = 30.sp, color = Color.Gray),
                    color = Color(0xFFA494AC)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "=",
                        style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.SemiBold),
                        color = Color(0xFFA494AC)
                    )

                    Text(
                        text = "10,000",
                        style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.SemiBold),
                        color = Color(0xFFA494AC)
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(44.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            val buttons = buttonList()

            items(buttons.size) { index ->
                CalculatorButton(buttons[index])
            }
        }
    }
}

@Composable
fun CalculatorButton(
    btn: String,
    modifier: Modifier = Modifier
) {

    FloatingActionButton(
        onClick = { },
        modifier = modifier.size(70.dp),
        shape = RoundedCornerShape(28.dp),
        contentColor = Color(0xFFA494AC),
        containerColor = getColor(btn),
    ) {
        if (btn == "Back") {
            Image(
                painter = painterResource(id = R.drawable.backspace),
                contentDescription = "backspace",
                modifier = Modifier.size(30.dp),
                colorFilter = ColorFilter.tint(Color(0xFF8E6E6B))
            )
        } else {
            Text(
                text = btn,
                fontSize = 30.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

fun getColor(btn: String): Color {
    return when (btn) {
        "=" -> Color(0xFFA495AC)
        "Back" -> Color(0xFFCFA7A3)
        "%", "÷", "*", "-", "+", "/" -> Color(0xFFEBE0F0)
        else -> Color(0xFFF3EBF8)
    }
}

