package com.example.composecourseyt.ui.cardotherflow

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
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.PaymentActivity
import com.example.composecourseyt.ui.cardotherflow.balanceenquiry.AccountTypeBalanceEnquiryActivity
import com.example.composecourseyt.ui.cardotherflow.cashadvance.CashAdvanceActivity
import com.example.composecourseyt.ui.cardotherflow.cashback.CashBackActivity
import com.example.composecourseyt.ui.cardotherflow.cashrefund.CashRefundActivity

class CardOtherActivity: ComponentActivity() {
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
//                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ){
//                PriceForInsertWithdraw(amount)
                CardOtherPage(amount, onBackClicked = { onBackPressed() })


            }
        }
    }
}
@Composable
fun CardOtherPage(
    amount: String?,
    onBackClicked: () -> Unit

){
    val context= LocalContext.current
    Column(
        modifier= Modifier
            .fillMaxSize()
    ) {
        CardOtherHeader(onBackClicked)
        CardOptions(
            onBalanceEnquiryClick =  {
                val intent = Intent(context, AccountTypeBalanceEnquiryActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onCashRefundClick =  {
                val intent = Intent(context, CashRefundActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onCashAdvanceClick =  {
                val intent = Intent(context, CashAdvanceActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
            onCashBackClick =  {
                val intent = Intent(context, CashBackActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
        )

    }
}

@Composable
fun CardOtherHeader(onBackClicked: () -> Unit){

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
        Spacer(modifier = Modifier.width(82.dp))
        Text(
            text = "Card(Other)",
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(214.dp)
                .height(29.dp)
        )

    }

}

@OptIn(ExperimentalTextApi::class)
@Composable
fun CardOptions(
    onBalanceEnquiryClick: ()-> Unit,
    onCashRefundClick: ()->Unit,
    onCashAdvanceClick: ()->Unit,
    onCashBackClick: ()->Unit
){
    val context = LocalContext.current

    Spacer(modifier = Modifier.height(54.4.dp))
    Column(
        modifier = Modifier.padding(start = 22.dp)
    ) {
        Text(
            text = "Card(Others)",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF222222),
            modifier = Modifier
                .fillMaxWidth()
                .height(17.dp)

        )
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x5E496779),
                    ambientColor = Color(0x5E496779)
                )
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onBalanceEnquiryClick() }
        ) {
            Text(
                text = "Balance Enquiry",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.White,
                style = TextStyle(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
                    )
                )
            )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x5E496779),
                    ambientColor = Color(0x5E496779)
                )
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onCashRefundClick() }
        ) {
            Text(
                text = "Cash Refund",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

            )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x5E496779),
                    ambientColor = Color(0x5E496779)
                )
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onCashAdvanceClick() }
        ) {
            Text(
                text = "Cash Advance",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x5E496779),
                    ambientColor = Color(0x5E496779)
                )
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
                .clickable { onCashBackClick() }
        ) {
            Text(
                text = "Cash Back",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x5E496779),
                    ambientColor = Color(0x5E496779)
                )
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
//                .clickable { onClickAccountType() }
        ) {
            Text(
                text = "Reversal Transfer",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x5E496779),
                    ambientColor = Color(0x5E496779)
                )
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
//                .clickable { onClickAccountType() }
        ) {
            Text(
                text = "Pre-Auth",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x5E496779),
                    ambientColor = Color(0x5E496779)
                )
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
//                .clickable { onClickAccountType() }
        ) {
            Text(
                text = "Sales Completion",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x5E496779),
                    ambientColor = Color(0x5E496779)
                )
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
//                .clickable { onClickAccountType() }
        ) {
            Text(
                text = "Airtime and Data",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,

                )

        }
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x5E496779),
                    ambientColor = Color(0x5E496779)
                )
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .width(330.dp)
                .height(63.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)
//                .clickable { onClickAccountType() }
        ) {
            Text(
                text = "Bills Payment",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,
                modifier = Modifier
                )
            Spacer(modifier = Modifier.height(74.dp))
        }
    }
}

