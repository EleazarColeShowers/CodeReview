package com.example.composecourseyt.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composecourseyt.R
import com.example.composecourseyt.data.local.entities.Transaction
import com.example.composecourseyt.ui.cashflow.CashActivity
import com.example.composecourseyt.ui.cashflow.cashdeposit.InsertCardActivity
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class PaymentActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val amount = intent.getStringExtra("amount")

        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra("amount", amount)

        setContent {
            PaymentPage(
                amount, onBackClicked = { onBackPressed() },
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentPage(
    amount: String?,
    onBackClicked: () -> Unit,
    transactionViewModel: TransactionViewModel = hiltViewModel()
) {
    val zone = ZoneId.of("Europe/Berlin")
    val isSwappedNonCash = remember { mutableStateOf(true) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFECEEF0))
    ) {
        Column(modifier = Modifier.weight(1f)) {
            PaymentMethod(amount, onBackClicked, isSwappedNonCash)
            PayBy(
                onEnterCardClicked = {
                    // Start the PaymentActivity when "Enter" is clicked
                    val intent = Intent(context, InsertCardActivity::class.java)
                    intent.putExtra("amount", amount)
                    context.startActivity(intent)
                },
                onEnterCashClicked = {
                    val timeStamp = DateTimeFormatter.
                    ofPattern("dd-MM-yyyy HH:mm:ss")
                        .withZone(zone)
                        .format(Instant.now())

                    val transaction  = Transaction(amount = amount?:"", timestamp = timeStamp)

                    transactionViewModel.insertTransaction(transaction)
                    val intent = Intent(context, CashActivity::class.java)
                    intent.putExtra("amount", amount)
                    context.startActivity(intent)
                },

//                onEvent = onEvent(TransactionEvent.SetAmount)
            )
        }

    }
}

@Composable
fun PaymentMethod(
    amount: String?,
    onBackClicked: () -> Unit,
    isSwappedNonCash: MutableState<Boolean>

) {
    val returnArrow = painterResource(id = R.drawable.returnarrow)
    val nonCashTextColor = if (isSwappedNonCash.value) Color(0xFF042E46) else Color(0xFFC9C9C9)

    Column(
        modifier = Modifier
            .background(Color.White)
            .height(202.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .height(80.dp)
                .border(1.dp, Color(0xFFD1D1D1))
                .padding(bottom = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 24.dp, top = 40.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = returnArrow,
                    contentDescription = null,
                    modifier = Modifier
                        .size(34.dp)
                        .clickable { onBackClicked.invoke() }
                )
                Text(
                    text = "Payment Method",
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFDD0A35),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 90.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .height(56.dp)
                .border(1.dp, Color(0xFFD1D1D1))
                .padding(bottom = 15.dp)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = "Total bill: ",
                    fontSize = 16.sp,
                    color = Color(0xFF042E46),
                    modifier = Modifier
                        .padding(start = 24.dp, top = 14.dp)
                )
                Text(
                    text = " ₦$amount.00",
                    fontSize = 22.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFDD0A35),
                    modifier = Modifier
                        .padding(start = 187.dp, top = 11.dp)
                )
            }
        }
        Row {
            Text(
                text = "Means of Payment",
                fontSize = 22.sp,
                fontWeight = FontWeight(500),
                color = nonCashTextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 15.dp, start = 48.dp, bottom = 7.dp),

                )


        }
    }
}
@Composable
fun PayBy(
    onEnterCardClicked: () -> Unit,
    onEnterCashClicked: () -> Unit,
//    state: TransactionState,
//    onEvent: (TransactionEvent) ->Unit
) {

    var isSwapped by remember { mutableStateOf(false) }
    val column1TextColor = if (isSwapped) Color(0xFFDD0A35) else Color(0xFF555555)
    val column2TextColor = if (isSwapped) Color(0xFF555555) else Color(0xFFDD0A35)

    val column1Image = if (isSwapped) painterResource(id = R.drawable.credit_card_2) else painterResource(id = R.drawable.credit_card_1)
    val column2Image = if (isSwapped) painterResource(id = R.drawable.gradientcash) else painterResource(id = R.drawable.cashh_2)

    Row(
        modifier = Modifier
            .padding(top = 23.dp, start = 18.dp, end = 18.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .shadow(
                    elevation = 30.dp,
                    spotColor = Color(0x141B1F44),
                    ambientColor = Color(0x141B1F44)
                )
                .padding(0.5.dp)
                .width(180.dp)
                .height(115.dp)
                .background(color = Color(0xFFFBFBFB), shape = RoundedCornerShape(size = 16.dp))
                .padding(start = 18.dp, top = 22.dp, end = 18.dp, bottom = 22.dp)
                .clickable {
                    isSwapped = false
                    onEnterCardClicked()
                }
        ) {
            Row {
                Image(
                    painter = column1Image,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 18.5.dp, top = 18.dp)
                )
                Text(
                    text = "CARD",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = column2TextColor,
                    modifier = Modifier
                        .padding(start = 18.13.dp, top = 23.dp)

                )
            }
        }
        Spacer(modifier = Modifier.width(30.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .shadow(
                    elevation = 30.dp,
                    spotColor = Color(0x141B1F44),
                    ambientColor = Color(0x141B1F44)
                )
                .width(180.dp)
                .height(115.dp)
                .background(color = Color(0xFFFBFBFB), shape = RoundedCornerShape(size = 16.dp))
                .padding(start = 20.dp, top = 22.dp, end = 18.dp, bottom = 22.dp)
                .clickable {
                    isSwapped = true // Update to swapped state
                    onEnterCashClicked()
                }
        ) {
            Row{
                Image(
                    painter = column2Image,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 15.dp, top = 18.dp)
                        .size(33.dp)
                )
                Text(
                    text = "CASH",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = column1TextColor,
                    modifier = Modifier
                        .padding(start = 18.13.dp, top = 23.dp)
                )
            }
        }
    }
}