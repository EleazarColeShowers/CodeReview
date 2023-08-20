package com.example.composecourseyt.ui.cardotherflow.cashadvance

import android.content.Intent
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.PaymentActivity
import com.example.composecourseyt.ui.cashflow.cashwithdraw.AccountOptionWithdrawal
import com.example.composecourseyt.ui.cashflow.cashwithdraw.PriceForInsertWithdraw

class CashAdvanceActivity : ComponentActivity() {
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
                verticalArrangement = Arrangement.Top
            ){
                CashAdvancePage(amount, onBackClicked = { onBackPressed() })


            }
        }
    }
}
@Composable
fun CashAdvancePage(
    amount: String?,
    onBackClicked: () -> Unit

){
    val context= LocalContext.current
    Column(
        modifier= Modifier
            .fillMaxSize()
    ) {
        CashAdvanceHeader(onBackClicked)
        PriceForInsertWithdraw(amount)
        AccountOptionWithdrawal(
            onClickAccountType = {
                val intent = Intent(context, InsertCardCashAdvanceActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
        )

    }
}

@Composable
fun CashAdvanceHeader(onBackClicked: () -> Unit){

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
            text = "Cash Advance",
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(214.dp)
                .height(29.dp)
        )

    }

}

