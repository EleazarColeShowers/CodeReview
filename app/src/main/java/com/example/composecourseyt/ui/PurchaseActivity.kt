package com.example.composecourseyt.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.otherflows.BottomBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PurchaseActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                PurchasePage()
            }
        }
    }
}
@Composable
fun PurchasePage(viewModel: TransactionViewModel = hiltViewModel()){
    val clickedNumber = remember { mutableStateOf("") }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
        // Handle any result if needed
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFECEEF0))
    ) {
        Column(modifier = Modifier.weight(1f)) {
            EnterAmount()
            Amount(clickedNumber.value){ number ->
                clickedNumber.value += number
            }
            Numbers(
                onClickNumber = { number ->
                    clickedNumber.value += number
                },
                onDeleteNumber = {
                    if (clickedNumber.value.isNotEmpty()) {
                        clickedNumber.value = clickedNumber.value.dropLast(1)
                    }
                },
                onClearCallbacks = {
                    // Clear all callbacks
                    clickedNumber.value = ""
                },
                onEnterClicked = {
                    // Start the PaymentActivity when "Enter" is clicked
                    val intent = Intent(context, PaymentActivity::class.java)
                    intent.putExtra("amount", clickedNumber.value)
                    context.startActivity(intent)
                },

            )
        }
        BottomBar()
    }
}
@Composable
fun EnterAmount() {
    val dashboardMenu = painterResource(id = R.drawable.dashboard_menu)

    Column(
        modifier = Modifier
            .background(Color.White)
            .height(146.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .border(1.dp, Color(0xFFD1D1D1))
                    .padding(bottom = 15.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 40.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = dashboardMenu,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Enter Amount",
                        fontSize = 22.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFDD0A35),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 120.dp)
                    )
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Purchase",
                fontSize = 16.sp,
                color = Color(0xFF042E46),
                modifier = Modifier
                    .padding(start = 24.dp, top = 14.dp)
            )
        }
    }
}

@Composable
fun Amount(value: String, onClickNumber: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 7.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 5.dp))
            .height(320.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "Input Price",
                    fontSize = 16.sp,
                    color = Color(0xFFDD0A35),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(82.dp)
                )
                Text(
                    text = "₦$value.00",
                    fontSize = 70.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF042E46),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable { onClickNumber("1") }
                )
            }
        }
    }
}

@Composable
fun Numbers(onClickNumber: (String) -> Unit, onDeleteNumber: () -> Unit, onClearCallbacks: () -> Unit, onEnterClicked: () -> Unit) {
    val deleteNumber = painterResource(id = R.drawable.deletecalc)
    val customTextColor = Color(0XFF042E46)
    Row{


        Column{
            Row(
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
                    .width(300.dp)

            ) {
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(56.01266.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "1"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )

                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(56.01266.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "2"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(56.01266.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "3"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
                    .width(300.dp)
            ) {
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(56.01266.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "4"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(56.01266.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "5"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(56.01266.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "6"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
                    .width(300.dp)
            ) {
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(56.01266.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "7"
                    Text(
                        text =number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(56.01266.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "8"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(56.01266.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "9"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
                    .width(300.dp)
            ) {
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(56.01266.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "C",
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber("0")
                                onClearCallbacks()
                            }
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(56.01266.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "0"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .width(85.45021.dp)
                        .height(56.01266.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val number = "00"
                    Text(
                        text = number,
                        fontSize = 28.sp,
                        color = Color(0xFF2A3256),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                onClickNumber(number)
                            }
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .width(90.dp)
                    .height(116.dp)
                    .padding(top = 10.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 5.dp))
                    .clickable { onDeleteNumber() }
            ) {
                Image(
                    painter = deleteNumber,
                    contentDescription = null,
                    modifier= Modifier
                        .padding(top = 35.dp, start = 25.dp, bottom = 46.dp)
                        .width(33.33341.dp)
                        .height(25.dp)

                )
            }
            Column(
                modifier = Modifier
                    .width(90.dp)
                    .height(147.dp)
                    .padding(top = 20.dp)
                    .background(color = customTextColor, shape = RoundedCornerShape(size = 5.dp))
                    .clickable {
                        // Invoke the "Enter" callback
                        onEnterClicked()
                    }

            ) {
                Text(
                    text = "ENTER",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier
                        .padding(start = 18.dp, top=45.dp)

                )
            }
        }
        Amount(value = onClickNumber.toString(), onClickNumber = onClickNumber)
    }
}
