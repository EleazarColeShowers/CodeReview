package com.example.composecourseyt.ui.otherflows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.remember
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
import com.example.composecourseyt.R
import java.text.SimpleDateFormat
import java.util.Locale

class CustomerPrintActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val amount = intent.getStringExtra("amount")
        setContent {
            Column (
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
            )
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
                text = "$currentDateAndTime pm",
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
            Spacer(modifier = Modifier.width(195.dp))
            Text(
                text = "Cash Deposit",
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
                text = "Card Number",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(190.dp))
            Text(
                text = "5199********8900",
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
                text = "Card Type",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(237.dp))
            Text(
                text = "Debit Card",
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
                text = "Account Type",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(235.dp))
            Text(
                text = "Savings",
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
                text = "Expiry Date",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(252.dp))
            Text(
                text = "04/24",
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
                text = "Stan",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(275.dp))
            Text(
                text = "294082",
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
                text = "Authorization Code",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(210.dp))
            Text(
                text = "294082",
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
                text = "Authorization Method",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(215.dp))
            Text(
                text = "PIN",
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
                text = "Response Code",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF555555),
            )
            Spacer(modifier = Modifier.width(245.dp))
            Text(
                text = "91",
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
fun CustomerFooter(gradient: Brush,){
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
                .padding(start= 165.dp)
        )
    }
}
