package com.example.composecourseyt.ui.cardotherflow.cashrefund

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.PaymentActivity
import com.example.composecourseyt.ui.cashflow.cashwithdraw.PriceForInsertWithdraw

class CashRefundActivity : ComponentActivity() {
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
                CashRefundPage(amount, onBackClicked = { onBackPressed() })


            }
        }
    }
}

@Composable
fun CashRefundPage(
    amount: String?,
    onBackClicked: () -> Unit

){
    val context= LocalContext.current
    Column(
        modifier= Modifier
            .fillMaxSize()
    ) {
        CashRefundHeader(onBackClicked)
        PriceForInsertWithdraw(amount)
        CashRefundForm(gradient = Brush.horizontalGradient(
            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280))
        ),
            onClickNext = {
                val intent = Intent(context, InsertCardCashRefundActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            }
        )



    }
}

@Composable
fun CashRefundHeader(onBackClicked: () -> Unit){

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
            text = "Cash Refund",
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(214.dp)
                .height(29.dp)
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CashRefundForm(gradient: Brush, onClickNext: () -> Unit) {
    var rnnText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 22.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Cash Refund",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(147.dp)
                .height(17.dp)
        )

        Spacer(modifier = Modifier.height(27.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = rnnText,
                onValueChange = { newText -> rnnText = newText },
                label = { Text("Enter RNN") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Perform action when the "Done" button is pressed on the keyboard
                    }
                ),
                modifier = Modifier
                    .width(326.dp)
                    .padding(8.dp)
            )


            Spacer(modifier = Modifier.height(119.dp))

            Column(
                modifier = Modifier
                    .padding(start = 0.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .shadow(
                            elevation = 40.dp,
                            spotColor = Color(0x08000000),
                            ambientColor = Color(0x08000000)
                        )
                        .width(350.dp)
                        .height(51.dp)
                        .background(gradient, shape = RoundedCornerShape(size = 8.dp))
                        .clickable {
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
                            .padding(top = 15.dp, start = 130.dp)
                    )
                }
            }
        }
    }
}
