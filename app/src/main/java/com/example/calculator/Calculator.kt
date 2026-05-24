package com.example.calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.CalUtil.calculate

fun buttonList() = listOf(
    "%","÷","*","Back",
    "7","8","9","-",
    "4","5","6","+",
    "1","2","3","/",
    "AC","0",".","="
)

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
) {

    var inputString by remember { mutableStateOf("") }
    var answerString by remember { mutableStateOf("") }

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
                    text = inputString,
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
                        text = answerString,
                        style = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.SemiBold),
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
                CalculatorButton(
                    btn = buttons[index],
                    onButtonClick = { buttonSymbol ->
                        println(buttonSymbol)

                        when (buttonSymbol) {
                            "=" -> {
                                answerString = calculate(inputString)
                            }
                            "AC" -> {
                                inputString = ""
                                answerString = ""
                            }
                            else -> {
                                inputString += buttonSymbol
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CalculatorButton(
    btn: String,
    modifier: Modifier = Modifier,
    onButtonClick: (String) -> Unit
) {

    FloatingActionButton(
        onClick = { onButtonClick(btn) },
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




//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CalculatorPreviewLightInitial() {
//    Calculator(
//        state = CalculatorState(expression = "", result = "0")
//    )
//}
//
//@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun CalculatorPreviewDarkInitial() {
//    Calculator(
//        state = CalculatorState(expression = "", result = "0")
//    )
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CalculatorPreviewLightInput() {
//    Calculator(
//        state = CalculatorState(expression = "123", result = "123")
//    )
//}
//
//@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun CalculatorPreviewDarkInput() {
//    Calculator(
//        state = CalculatorState(expression = "123", result = "123")
//    )
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CalculatorPreviewLightOperation() {
//    Calculator(
//        state = CalculatorState(expression = "123 + ", result = "123")
//    )
//}
//
//@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun CalculatorPreviewDarkOperation() {
//    Calculator(
//        state = CalculatorState(expression = "123 + ", result = "123")
//    )
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CalculatorPreviewLightResult() {
//    Calculator(
//        state = CalculatorState(expression = "123 + 456", result = "579")
//    )
//}
//
//@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun CalculatorPreviewDarkResult() {
//    Calculator(
//        state = CalculatorState(expression = "123 + 456", result = "579")
//    )
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CalculatorPreviewLightError() {
//    Calculator(
//        state = CalculatorState(expression = "5 / 0", result = "Error")
//    )
//}
//
//@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun CalculatorPreviewDarkError() {
//    Calculator(
//        state = CalculatorState(expression = "5 / 0", result = "Error")
//    )
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CalculatorPreviewLightDecimal() {
//    Calculator(
//        state = CalculatorState(expression = "3.14 * 2", result = "6.28")
//    )
//}
//
//@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun CalculatorPreviewDarkDecimal() {
//    Calculator(
//        state = CalculatorState(expression = "3.14 * 2", result = "6.28")
//    )
//}
