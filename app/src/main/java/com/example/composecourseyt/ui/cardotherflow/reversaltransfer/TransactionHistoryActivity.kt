package com.example.composecourseyt.ui.cardotherflow.reversaltransfer

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.PaymentActivity

class TransactionHistoryActivity  : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val amount = intent.getStringExtra("amount")

        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra("amount", amount)
        setContent {
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                TransactionHistoryPage( onBackClicked = { onBackPressed() })
            }
        }
    }
}

@Composable
fun TransactionHistoryPage(onBackClicked: () -> Unit){
    Column( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        TransactionHistoryHeader (onBackClicked)
        TransactionTime()
        TransactionHistoryPortion()
        TransactionHistoryPortion()
    }

}

@Composable
fun TransactionHistoryHeader(onBackClicked: () -> Unit){
    val returnArrow= painterResource(id = R.drawable.returnarrow)
    Row(
        modifier = Modifier
            .padding(top=17.5.dp)
    ){
        Image(
            painter = returnArrow,
            contentDescription = null,
            modifier = Modifier
                .padding(1.dp)
                .width(36.dp)
                .height(36.dp)
                .clickable { onBackClicked.invoke() }

        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(
            text = "Transaction History",
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(214.dp)
                .height(29.dp)
        )

    }

}

@Composable
fun TransactionTime(){
    val transactionTime= painterResource(id = R.drawable.transactiontime)
    Row(
        modifier= Modifier
            .padding(top=30.dp, start = 10.dp)
    ) {
        Image(
            painter = transactionTime,
            contentDescription =null,
            modifier= Modifier
                .padding(1.dp)
                .width(24.dp)
                .height(24.dp)
        )
        Text(
            text= "Select Date",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            modifier= Modifier
                .padding(start=8.dp, top=2.dp)
        )
        Text(
            text = "MM/DD/YY",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            modifier= Modifier
                .padding(start=151.dp)
            )
    }
}

@Composable
fun TransactionHistoryPortion(){
    val card= painterResource(id = R.drawable.credit_card_2)
    val transfer= painterResource(id = R.drawable.transfers)
    val cash= painterResource(id = R.drawable.cashh_2)

    Column(
        modifier = Modifier
            .padding(top=24.dp, start = 10.dp)
    ) {
        Row() {
            Text(
                text = "August 8, 2023",
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF222222),
                modifier = Modifier
                    .width(88.dp)
                    .height(17.dp)
            )
            Spacer(modifier = Modifier.width(210.dp))
            Text(
                text = "See all",
                fontSize = 10.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF222222),
                modifier = Modifier
                    .width(32.dp)
                    .height(12.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.75.dp))

        Column (
            modifier = Modifier
                .border(2.dp, Color(0x5E8AB6A1), shape = RoundedCornerShape(8.dp))

                .width(340.dp)
                .height(100.dp)
                .padding(start = 16.dp, top = 10.dp, end = 16.dp, bottom = 10.dp)

        ){
            Row(
            ) {
                Text(
                    text = "10 July.2023",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(85.dp)
                        .height(17.dp)
                )
                Spacer(modifier = Modifier.width(132.77.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Failed",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    modifier= Modifier
                        .width(79.dp)
                        .height(24.dp)
                        .background(
                            color = Color(0xDEDD0A35),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(start = 25.dp, top = 5.dp, end = 10.dp, bottom = 6.dp)
                )
            }
            Row(
                modifier = Modifier . padding(top = 8.dp)
            ){
                Text(
                    text = "3:45pm",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )
            }
            Row(
                modifier = Modifier . padding(top = 8.dp)
            ) {
                Text(
                    text = "₦7,000",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(800),
                    color = Color(0xFF000000),
                    letterSpacing = 1.sp,
                    modifier = Modifier
                        .width(74.dp)
                        .height(26.dp)
                )
                Spacer(modifier = Modifier.width(155.dp))

                Image(
                    painter = card,
                    contentDescription = null,
                    modifier= Modifier
                        .padding(1.dp)
                        .width(16.dp)
                        .height(16.dp)
                )
                Text(
                    text = "Card(Other)",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(61.dp)
                        .height(12.dp)

                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column (
            modifier = Modifier
                .border(2.dp, Color(0x5E8AB6A1), shape = RoundedCornerShape(8.dp))
                .width(340.dp)
                .height(100.dp)
                .padding(start = 16.dp, top = 10.dp, end = 16.dp, bottom = 10.dp)

        ){
            Row(
            ) {
                Text(
                    text = "10 July.2023",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(85.dp)
                        .height(17.dp)
                )
                Spacer(modifier = Modifier.width(132.77.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Failed",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    modifier= Modifier
                        .width(79.dp)
                        .height(24.dp)
                        .background(
                            color = Color(0xDEDD0A35),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(start = 25.dp, top = 5.dp, end = 10.dp, bottom = 6.dp)
                )
            }
            Row(modifier = Modifier . padding(top = 8.dp)) {
                Text(
                    text = "12:05pm",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )

            }
            Row(
                modifier = Modifier . padding(top = 8.dp)
            ) {
                Text(
                    text = "₦7,000",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(800),
                    color = Color(0xFF000000),
                    letterSpacing = 1.sp,
                    modifier = Modifier
                        .width(74.dp)
                        .height(26.dp)
                )
                Spacer(modifier = Modifier.width(155.dp))

                Image(
                    painter = transfer,
                    contentDescription = null,
                    modifier= Modifier
                        .padding(1.dp)
                        .width(16.dp)
                        .height(16.dp)
                )
                Text(
                    text = "Transfer",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(61.dp)
                        .height(12.dp)

                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Column (
            modifier = Modifier
                .border(2.dp, Color(0x5E8AB6A1), shape = RoundedCornerShape(8.dp))
                .width(340.dp)
                .height(100.dp)
                .padding(start = 16.dp, top = 10.dp, end = 16.dp, bottom = 10.dp)

        ){
            Row(
            ) {
                Text(
                    text = "10 July.2023",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(85.dp)
                        .height(17.dp)
                )
                Spacer(modifier = Modifier.width(132.77.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Successful",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                    modifier= Modifier
                        .width(79.dp)
                        .height(24.dp)
                        .background(
                            color = Color(0xDE01AD2A),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(start = 20.dp, top = 5.dp, end = 10.dp, bottom = 6.dp)
                )
            }
            Row(modifier = Modifier . padding(top = 8.dp)) {
                Text(
                    text = "01:34pm",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )

            }
            Row(
                modifier = Modifier . padding(top = 8.dp)
            ) {
                Text(
                    text = "₦32,000",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(800),
                    color = Color(0xFF000000),
                    letterSpacing = 1.sp,
                    modifier = Modifier
                        .width(74.dp)
                        .height(26.dp)
                )
                Spacer(modifier = Modifier.width(155.dp))

                Image(
                    painter = cash,
                    contentDescription = null,
                    modifier= Modifier
                        .padding(1.dp)
                        .width(16.dp)
                        .height(16.dp)
                )
                Text(
                    text = "Cash",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .width(61.dp)
                        .height(12.dp)

                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))


    }
}
