package com.example.composecourseyt.ui.otherflows

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composecourseyt.R
import com.example.composecourseyt.data.local.entities.Transaction
import com.example.composecourseyt.ui.TransactionViewModel
import com.example.composecourseyt.ui.cardotherflow.CardOtherActivity
import com.example.composecourseyt.ui.cardpurchaseflow.AccountTypePurchaseActivity
import com.example.composecourseyt.ui.transferflow.TransferActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
@AndroidEntryPoint
//ONBOARDING PAGE
class MainActivity : ComponentActivity(){
//    private late init var transactionRepo: TransactionRepo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        transactionRepo = TransactionRepo(AppDatabase.getInstance(this).transactionDao())

        setContent {
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
//                LoginPage()
                OnBoardPage(amount = String())
            }
        }
    }
}
@Composable
fun OnBoardPage(
    amount: String?,
    transactionViewModel: TransactionViewModel = hiltViewModel()
 ){
    val zone = ZoneId.of("Africa/Lagos")

    val clickedNumber = remember { mutableStateOf("") }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
        // Handle any result if needed
    }

    Column(
        modifier= Modifier
            .fillMaxSize()
    ) {
        ImageAndAddress()

        TransactionHistory(gradient = Brush.horizontalGradient(
            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
        ))

        AmountPortion(clickedNumber.value){ number ->
            clickedNumber.value += number
        }

        NumbersPortion(onClickNumber = { number ->
            clickedNumber.value += number
        },
            onDeleteNumber = {
                if (clickedNumber.value.isNotEmpty()) {
                    clickedNumber.value = clickedNumber.value.dropLast(1)
                }
            },
            onClearAll = {
                clickedNumber.value = " "
            })
        PaymentPortion(
//            amount = clickedNumber.value,
            onClickCash = {
                // Start the PaymentActivity when "Enter" is clicked
                val timeStamp = DateTimeFormatter.
                ofPattern("dd-MM-yyyy HH:mm:ss")
                    .withZone(zone)
                    .format(Instant.now())

                val transaction  = Transaction(amount = clickedNumber.value, timestamp = timeStamp)

                transactionViewModel.insertTransaction(transaction)
                val intent = Intent(context, CustomerPrintActivity::class.java)
                intent.putExtra("amount", clickedNumber.value)
                context.startActivity(intent)
            },
            onClickCardPurchase={
                val intent= Intent(context, AccountTypePurchaseActivity::class.java)
                intent.putExtra("amount",clickedNumber.value)
                context.startActivity(intent)
            },
            onClickTransfer={
                val intent= Intent(context, TransferActivity::class.java)
                intent.putExtra("amount", clickedNumber.value)
                context.startActivity(intent)
            },
            onClickCardOther={
                val intent= Intent(context, CardOtherActivity::class.java)
                intent.putExtra("amount", clickedNumber.value)
                context.startActivity(intent)
            }
        )

        TransactionPortion()

        Footer()
    }
}

@Composable
fun ImageAndAddress(){
    val calenderIcon= painterResource(id = R.drawable.calender)
    Row(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x5E838282),
                ambientColor = Color(0x5E838282)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 8.dp)
            )
            .fillMaxWidth()
            .height(120.dp)
            .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
            .padding(top = 7.dp, start = 24.dp, end = 24.dp)
    ) {
       Image(
            painter = painterResource(R.drawable.cyberpay_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .width(108.dp)
                .height(108.dp)
                .padding(start = 2.dp, top = 6.dp)
        )
        
        Column(
            modifier = Modifier
                .padding(top= 20.dp, start=80.dp)
        ) {
            Text(
                text= "John Doe Inc",
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF222222),
                modifier= Modifier
                    .width(102.dp)
                    .height(19.dp)
            )
            Text(
                text = "Shop 15, behind computer village, Bariga, Lagos state.",
                fontSize = 10.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF8E9195),
                letterSpacing = 0.03.sp,
                modifier = Modifier
                    .width(131.dp)
                    .height(24.dp)
                )
            Row() {
                Image(
                    painter = calenderIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(1.dp)
                        .width(16.dp)
                        .height(16.dp)
                )
                CurrentDayText()
            }



        }
    }

}

@Composable
fun CurrentDayText() {
    val currentDate = remember { Calendar.getInstance().time }
    val formattedDate = remember {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.format(currentDate)
    }

    Text(
        text = "$formattedDate",
        fontSize = 14.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFF222222),
        letterSpacing = 0.04.sp,
        modifier= Modifier
            .width(91.dp)
            .height(18.dp)
    )
}

@Composable
fun TransactionHistory(gradient: Brush){
    val eodBtn= painterResource(id = R.drawable.tabler_report_money)
    val historyBtn= painterResource(id = R.drawable.history_icon)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp)
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x5E838282),
                ambientColor = Color(0x5E838282)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 8.dp)
            )
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .height(204.dp)
                .padding(top = 14.dp)
        ) {
            Text(
                text = "Today",
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF222222),
            )
            Text(
                text = "₦235,000.00",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF222222),
                letterSpacing = 1.sp,
                modifier = Modifier
                    .padding(top = 19.5.dp)
            )

            Spacer(modifier = Modifier.height(17.dp)) // Add Spacer with 17.dp padding top

            Text(
                text = "7 Transactions",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF222222),
                modifier = Modifier
                    .background(color = Color(0x0D26FF02), shape = RoundedCornerShape(size = 24.dp))
                    .padding() // Adjust padding as needed
            )
            Spacer(modifier = Modifier.height(36.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                onClick = {},
                modifier = Modifier
//                    .padding(horizontal = 8.dp, vertical = 2.dp)
                    .background(gradient, shape = RoundedCornerShape(size = 8.dp))
                    .padding(start = 1.dp, top = 3.dp, bottom = 3.dp)
                    .width(130.dp)
                    .height(39.dp)


            ) {
                Row {
                        Image(
                            painter = eodBtn,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 6.dp, top = 4.dp)
                                .width(13.dp)
                                .height(13.dp)
                        )
                        Text(
                            text = "End of Day",
                            fontSize = 13.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                            modifier= Modifier
                        )
                    }


            }

        }

        Spacer(modifier = Modifier.width(29.dp))

        Column(
            modifier = Modifier
                .height(204.dp)
                .padding(top = 14.dp)
        ) {
            Text(
                text = "Yesterday",
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF979797),
                textAlign = TextAlign.Center,
            )
            Text(
                text = "₦71,000.00",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF979797),
                letterSpacing = 1.sp,
                modifier = Modifier
                    .padding(top = 19.5.dp)
            )
            Spacer(modifier = Modifier.height(17.dp)) // Add Spacer with 17.dp padding top
            Text(
                text = "4 Transactions",
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF979797),
                modifier = Modifier
                    .background(color = Color(0x0D979797), shape = RoundedCornerShape(size = 24.dp))
            )

            Spacer(modifier = Modifier.height(36.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                onClick = {},
                modifier = Modifier
                    .background(gradient, shape = RoundedCornerShape(size = 8.dp))
                    .padding(start = 1.dp, top = 3.dp, bottom = 3.dp)
                    .width(130.dp)
                    .height(39.dp)


            ) {
                Row {
                    Image(
                        painter = historyBtn,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 6.dp, top = 4.dp)
                            .width(13.dp)
                            .height(13.dp)
                    )
                    Text(
                        text = "History",
                        fontSize = 13.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        modifier= Modifier
                    )
                }


            }
        }
    }
}

@Composable
fun AmountPortion( value: String,onClickNumber: (String) -> Unit){
    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 22.dp)
            .fillMaxWidth()

    ) {
        Text(
            text = "Start new Transaction",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(147.dp)
                .height(17.dp)
        )
        Spacer(modifier = Modifier.height(30.06.dp))
//        Spacer(modifier = Modifier.width(135.5.dp))
        Text(
            text = "Input Amount",
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFFDD0A35),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(19.dp)
        )

        Spacer(modifier = Modifier.height(12.94.dp))

        Text(
            text = "₦$value.00",
            fontSize = 24.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF042E46),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 8.dp, start = 120.dp)
                .clickable { onClickNumber("1") }
        )
    }
}

@Composable
fun NumbersPortion(onClickNumber: (String) -> Unit, onDeleteNumber: () -> Unit, onClearAll: ()-> Unit){

    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 0.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 40.dp, top = 10.dp)
                .fillMaxWidth()


        ) {
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .height(50.dp)
//                    .border(1.dp, Color(0xFF000000))
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 5.dp)
                    ),
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
                    .width(80.dp)
                    .height(50.dp)
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 5.dp)
                    )
                    .padding(start = 40.dp),
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
                    .padding(start = 60.dp),
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
                .padding(start = 40.dp, top = 10.dp)
                .fillMaxWidth()


        ) {
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .height(50.dp)
//                    .border(1.dp, Color(0xFF000000))
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 5.dp)
                    ),
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
                    .width(80.dp)
                    .height(50.dp)
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 5.dp)
                    )
                    .padding(start = 40.dp),
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
                    .padding(start = 60.dp),
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
                .padding(start = 40.dp, top = 10.dp)
                .fillMaxWidth()


        ) {
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .height(50.dp)
//                    .border(1.dp, Color(0xFF000000))
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 5.dp)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val number = "7"
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
                    .width(80.dp)
                    .height(50.dp)
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 5.dp)
                    )
                    .padding(start = 40.dp),
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
                    .padding(start = 60.dp),
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
                .padding(start = 40.dp, top = 10.dp)
                .fillMaxWidth()


        ) {
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .height(50.dp)
//                    .border(1.dp, Color(0xFF000000))
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 5.dp)
                    )
                    .clickable { onDeleteNumber() },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val delete= painterResource(id = R.drawable.deletecalc)
                Image(painter = delete,
                    contentDescription = null,
                    modifier = Modifier
                        .width(28.0752.dp)
                        .height(21.04749.dp)
                )



            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .height(50.dp)
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 5.dp)
                    )
                    .padding(start = 40.dp),
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
                    .padding(start = 50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val number = "C"
                Text(
                    text = number,
                    fontSize = 28.sp,
                    color = Color(0xFF2A3256),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            onClearAll()
                        }
                )
            }
        }
    }


}

@OptIn(ExperimentalTextApi::class)
@Composable
fun PaymentPortion(onClickCash: () -> Unit, onClickCardPurchase: ()-> Unit, onClickTransfer: ()->Unit, onClickCardOther: ()-> Unit) {
    val cash = painterResource(id = R.drawable.gradientcash)
    val card = painterResource(id = R.drawable.gradientcreditcard)
    val transfer = painterResource(id = R.drawable.gradienttransfer)
    val gradientColors = listOf(Color(0xFF134E5E), Color(0xFF71B280))

    Column(
        modifier = Modifier
            .padding(top = 24.95.dp, start = 10.dp)
    ) {
        Text(
            text = "Select Payment Method",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(162.dp)
                .height(17.dp)
        )
        Row(
            modifier = Modifier
                .padding(top = 29.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(134.dp)
                    .height(92.dp)
                    .clickable { onClickCash() }
                    .border(2.dp, Color(0x5E8AB6A1), shape = RoundedCornerShape(8.dp))


            ) {
                Image(
                    painter = cash, // Replace with your image resource
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 14.dp, start = 27.dp)
                        .size(24.dp)
                        .background(
                            color = Color(0x5EE1FD38),
                            shape = CircleShape
                        )
                )
                Text(
                    text = "Cash",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color= Color.White,
                    modifier = Modifier
                        .padding(top = 17.dp, start = 27.dp),
                    style = TextStyle(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                        )
                    ),
                )
            }


            Spacer(modifier = Modifier.width(70.dp))
            Column(
                modifier = Modifier
                    .width(134.dp)
                    .height(92.dp)
                    .clickable { onClickCash() }
                    .border(2.dp, Color(0x5E8AB6A1), shape = RoundedCornerShape(8.dp))
                    .clickable {
                        onClickCardPurchase()
                    }

            ) {
                Image(
                    painter = card,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 14.dp, start = 27.dp)
                        .size(24.dp)
                        .background(
                            color = Color(0x5E00EAF9),
                            shape = CircleShape
                        )
                )
                Text(
                    text = "Card(Purchase)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 17.dp, start = 27.dp),
                    style = TextStyle(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                        )
                    ),
                )

            }
        }

        Row(
            modifier = Modifier
                .padding(top = 29.dp,)
        ) {
            Column(
                modifier = Modifier
                    .width(134.dp)
                    .height(92.dp)
                    .clickable { onClickTransfer() }
                    .border(2.dp, Color(0x5E8AB6A1), shape = RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = transfer,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 14.dp, start = 27.dp)
                        .size(24.dp)
                        .background(
                            color = Color(0xFF51FF62),
                            shape = CircleShape
                        )
                )
                Text(
                    text = "Transfer",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 17.dp, start = 27.dp),
                    style = TextStyle(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                        )
                    ),
                )

            }
            Spacer(modifier = Modifier.width(70.dp))
            Column(
                modifier = Modifier
                    .width(134.dp)
                    .height(92.dp)
                    .clickable { onClickCash() }
                    .border(2.dp, Color(0x5E8AB6A1), shape = RoundedCornerShape(8.dp))
                    .clickable {
                        onClickCardOther()
                    }
            ) {
                Image(
                    painter = card,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 14.dp, start = 27.dp)
                        .size(24.dp)
                        .background(
                            color = Color(0x5EFD2FF5),
                            shape = CircleShape
                        )
                )
                Text(
                    text = "Card(Other)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 17.dp, start = 27.dp),
                    style = TextStyle(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                        )
                    ),
                )

            }
        }
    }
}

@Composable
fun TransactionPortion(){
    val card= painterResource(id = R.drawable.credit_card_2)
    val transfer= painterResource(id = R.drawable.transfers)
    val cash= painterResource(id = R.drawable.cashh_2)

    Column(
        modifier = Modifier
            .padding(top=24.dp, start = 10.dp)
    ) {
        Row() {
            Text(
                text = "Transactions",
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
                        .padding(start = 17.dp, top = 5.dp, end = 10.dp, bottom = 6.dp)
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
                        .padding(start = 17.dp, top = 5.dp, end = 10.dp, bottom = 6.dp)
                )
            }
            Row(modifier = Modifier . padding(top = 8.dp)) {
                Text(
                    text = "11:37am",
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
    }
}

@Composable
fun Footer() {
    Spacer(modifier = Modifier.height(28.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF070F13),
                shape = RoundedCornerShape(
                    topStart = 6.dp,
                    topEnd = 6.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            )
            .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp) // Adjust the top padding as needed
    ) {
        Text(
            text = "Powered by TruPay",
            fontSize = 12.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .padding(start= 125.dp)
            // Remove the width and height modifiers for the Text composable
        )
    }
}
