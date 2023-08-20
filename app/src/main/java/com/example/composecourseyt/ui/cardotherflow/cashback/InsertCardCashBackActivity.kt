package com.example.composecourseyt.ui.cardotherflow.cashback

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.composecourseyt.ui.cashflow.cashwithdraw.InsertCardWithdraw
import com.example.composecourseyt.ui.cashflow.cashwithdraw.PriceForInsertWithdraw
import com.example.composecourseyt.ui.otherflows.EnterPinActivity

class InsertCardCashBackActivity : ComponentActivity() {
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
                InsertCardCashBack(amount, onBackClicked = { onBackPressed() },
                )
            }

        }
    }
}

@Composable
fun InsertCardCashBack(
    amount: String?,
    onBackClicked: () -> Unit
){
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CashBackHeader(onBackClicked)
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