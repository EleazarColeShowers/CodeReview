package com.example.composecourseyt.ui.otherflows

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.composecourseyt.R
import java.text.SimpleDateFormat
import java.util.Locale

class CustomerPrintActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val amount = intent.getStringExtra("amount")


        setContent {
            var isPopupVisible by remember { mutableStateOf(true) }

            if (isPopupVisible) {
                PopupScreen(
                    amount = amount,
                    onDismiss = { isPopupVisible = false },
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                    ),
                    onClickClose ={
                        val intent = Intent(this@CustomerPrintActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    CustomerReceiptPage(amount)
                }
            }
        }
    }
}
@Composable
fun PopupScreen(amount: String?, onDismiss: () -> Unit,gradient: Brush,onClickClose: () -> Unit) {
    // Define the fixed width and height for the pop-up
    val popupWidth = 328.dp
    val popupHeight = 454.dp
    val successfulTransaction= painterResource(id = R.drawable.transaction_successful)
    val printIcon= painterResource(id = R.drawable.print_icon)
    val context = LocalContext.current


    // Create a Dialog to display the pop-up content
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Box(
            modifier = Modifier
                .size(popupWidth, popupHeight)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(7.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp)),                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = successfulTransaction,
                    contentDescription = null,
                    modifier = Modifier
                        .size(171.dp)
                )

                Text(
                    text = "Transaction Approved",
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                )
                Spacer(modifier = Modifier.height(69.dp))
                Column(
                    modifier = Modifier
                        .background(gradient, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                        .width(250.dp)
                        .height(20.dp)
                        .clickable {
                            onDismiss()
                        }
                ) {
                    Row() {
                        Text(
                            text = "Print Receipt",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                            modifier= Modifier
                                .padding(start = 70.dp)
                        )
                        Image(
                            painter = printIcon,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(1.dp)

                        )
                    }
                }
                Text(
                    text = "Home",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .padding(start = 5.dp, top = 16.5.dp)
                        .clickable {
                            onClickClose()
                        }
                    )
            }
        }
    }
}

@Composable
fun CustomerReceiptPage(
    amount: String?,
    ){
    val context = LocalContext.current


    Column(
        modifier= Modifier
            .background(color = Color.White)
    ) {
        CustomerHeader()
        MerchantInfo()
        PageContent(amount)
        CustomerFooter(
            gradient = Brush.horizontalGradient(
                colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
            ),
            onClickClose ={
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }
        )
    }
}

@Composable
fun CustomerHeader(){
    val logo= painterResource(id = R.drawable.cyberpay_logo)

    Column()
    {
        Image(
            painter = logo,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 11.dp, start = 120.dp)
                .size(140.dp)
        )
        Text(
            text = "Customer's Copy",
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color= Color.Black,
            modifier= Modifier
                .padding(start = 145.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "CYBER PAYMENT",
            fontSize = 16.sp,
            fontWeight = FontWeight(800),
            modifier = Modifier
                .padding(start = 135.dp)
        )
        Text(
            text = "Victoria Island, LA-LANG",
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF202020),
            textAlign = TextAlign.Center,
            modifier= Modifier
                .padding(start = 125.dp)
        )
    }
}

@Composable
fun MerchantInfo(){
    val currentDateAndTime = remember {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val currentDateTime = System.currentTimeMillis()
        dateFormat.format(currentDateTime)
    }
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 23.dp, top = 42.dp)
            ){
        Row() {
            Text(
                text = "Merchant ID:",
                fontSize = 10.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(80.dp))
            Text(
                text = "2060LA200002663 CP- 2060LA200002663",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,

            )

        }
        Row() {
            Text(
                text = "Date & Time",
                fontSize = 10.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(175.dp))
            Text(
                text = "$currentDateAndTime",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,

                )

        }

    }
}

@Composable
fun PageContent(amount: String?){
    Column(
        modifier = Modifier
            .padding(top = 49.dp, start = 97.dp) // Adjust padding as needed

    ) {
        Text(
            text = "Transaction Approved",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(182.dp)
                .height(37.dp)
                .background(color = Color(0x0D26FF02), shape = RoundedCornerShape(size = 24.dp))
                .padding(start = 16.dp, top = 10.dp)
        )
    }
    Column(
        modifier= Modifier.fillMaxWidth()
    ) {
        Row(
            modifier= Modifier
                .padding(start = 24.dp,top= 33.86.dp)
        ) {
            Text(
                text = "Terminal ID",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(235.dp))
            Text(
                text = "2037YU41",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 24.dp, top=15.14.dp)
        ) {
            Text(
                text = "Transaction Type",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(189.dp))
            Text(
                text = "Cash Payment",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 24.dp, top=15.14.dp)
        ) {
            Text(
                text = "Name",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(262.dp))
            Text(
                text = "John Doe",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 24.dp, top=15.14.dp)
        ) {
            Text(
                text = "RNN",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(244.dp))
            Text(
                text = "004145800636",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 24.dp, top=15.14.dp)
        ) {
            Text(
                text = "Amount",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(250.dp))
            Text(
                text = "₦$amount.00",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 24.dp, top=43.dp)
        ) {
            Text(
                text = "Total",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(257.dp))
            Text(
                text = "₦$amount.00",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF202020),
                textAlign = TextAlign.Right,
            )
        }

    }
}

@Composable
fun CustomerFooter(gradient: Brush,onClickClose: () -> Unit){
    Column(
        modifier = Modifier
            .padding(top= 37.dp)
    ) {
        Text(
            text = "Please retain your receipt. Thank you.",
            fontSize = 10.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF555555),
            modifier = Modifier
                .padding(start = 100.dp)
        )
        Text(
            text = "Powered by TruPay",
            fontSize = 12.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF042E46),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 20.dp, start = 132.dp)
        )
        Column(
            modifier = Modifier

                .shadow(
                    elevation = 40.dp,
                    spotColor = Color(0x08000000),
                    ambientColor = Color(0x08000000)
                )
                .width(327.dp)
                .height(80.dp)
                .padding(start = 45.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                .background(gradient, shape = RoundedCornerShape(size = 8.dp))
                .clickable {
//                    onClickNext()
                }

        ) {
            Text(
                text = "PRINT CUSTOMER'S COPY",
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .padding(start = 40.dp, top = 12.dp)
            )
        }
        Text(
            text = "Close",
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier
                .padding(start = 165.dp)
                .clickable {
                    onClickClose()
                }
        )
        Spacer(modifier = Modifier.height(73.dp))
    }
}
