package com.example.composecourseyt.ui.cashflow.cashwithdraw

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.composecourseyt.ui.otherflows.EnterPinActivity

class InsertCardWithdrawActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val amount = intent.getStringExtra("amount")
        setContent {
//            InsertionCard(amount) {
//                onBackPressed()
//            }
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
//            SuccessfulTransaction(amount)
                InsertCardWithdraw(amount, onBackClicked = { onBackPressed() },
                )
            }

        }
    }
}

@Composable
fun InsertCardWithdraw(
    amount: String?,
    onBackClicked: () -> Unit
){
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CashWithdrawalHeader(onBackClicked)
        PriceForInsertWithdraw(amount)
        InsertCardWithdraw(gradient = Brush.horizontalGradient(
            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
        ),
            onClickNext = {
                val intent = Intent(context, EnterPinActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            }
        )

    }
}

@Composable
fun PriceForInsertWithdraw(amount: String?){
    Column(
        modifier = Modifier
            .padding(top = 41.5.dp,)
            .fillMaxWidth()
    ) {
        Text(
            text = "Total Bill",
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFFDD0A35),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(19.dp)
                .padding(start = 7.dp)
        )

        Text(
            text = " ₦$amount.00",
            fontSize = 24.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF000000),
            letterSpacing = 1.sp,
            modifier = Modifier
                .padding(top=18.dp, start = 143.dp)
        )
    }
}

@Composable
fun InsertCardWithdraw(gradient: Brush, onClickNext: () -> Unit){
    val insertCard = painterResource(id = R.drawable.atm_machine)
    val context= LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, start = 22.dp)
    ) {
        Text(
            text = "Insert Card",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(260.dp)
                .height(17.dp)
                .padding(start = 0.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 85.dp, start = 45.dp, end = 63.dp)
        ) {
            // Child views.
            Text(
                text = "Insert Card",
                fontSize = 16.sp,
                color = Color(0xFFDD0A35),
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center
            )
            Image(
                painter = insertCard,
                contentDescription = null,
                modifier = Modifier
                    .width(249.dp)
                    .height(249.dp)
            )
            Spacer(modifier = Modifier.height(34.dp))
            Column(
                modifier = Modifier
                    .padding(start = 0.dp,)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier

                        .shadow(
                            elevation = 40.dp,
                            spotColor = Color(0x08000000),
                            ambientColor = Color(0x08000000)
                        )
                        .width(450.dp)
                        .height(51.dp)
                        .background(gradient, shape = RoundedCornerShape(size = 8.dp))
                        .clickable{
                            onClickNext()
                        }

                ) {
                    Text(
                        text = "Next",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .padding(top= 15.dp, start = 130.dp)
                    )
                }
            }
        }
    }
}