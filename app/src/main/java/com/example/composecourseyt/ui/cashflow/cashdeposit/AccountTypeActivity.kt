package com.example.composecourseyt.ui.cashflow.cashdeposit
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

@Suppress("DEPRECATION")
class AccountTypeActivity : ComponentActivity() {
//    private val viewModel: AccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val amount = intent.getStringExtra("amount")
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra("amount", amount)
//        viewModel.setAmount(amount)
        setContent {
//            AccountTypePage(amount) {
//                onBackPressed()
//            }
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ){
                TypeOfAccount(amount, onBackClicked = { onBackPressed() })
            }
        }
    }
}
/*
class AccountViewModel : ViewModel() {
    private val _amount = MutableStateFlow<String?>(null)

    val amount: MutableStateFlow<String?> get() = _amount

    fun setAmount(amount: String?) {
        viewModelScope.launch {
            _amount.emit(amount)
        }
    }
}

@Composable
fun AccountTypePage(amount: String?, onBackClicked: () -> Unit) {
    val isSwappedNonCash = remember { mutableStateOf(true) }
//    val viewModel: AccountViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFECEEF0))
    ) {
        Column(modifier = Modifier.weight(1f)) {
            AccountDash(amount, onBackClicked, isSwappedNonCash)
            AccountType(amount)
        }
    }
}

@Composable
fun AccountDash(
    amount: String?,
    onBackClicked: () -> Unit,
    isSwappedNonCash: MutableState<Boolean>
){
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
                text = "Account Type",
                fontSize = 22.sp,
                fontWeight = FontWeight(500),
                color = nonCashTextColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start = 43.dp, top = 14.dp),

                )
        }
    }
}
/*
@Composable
fun AccountType(amount: String?) {
    val context = LocalContext.current

    var selectedAccount by remember { mutableStateOf(AccountType.SAVING) }

    Column(modifier = Modifier.padding(top = 23.dp, start = 35.dp)) {
        AccountItem(
            accountType = AccountType.SAVING,
            selectedAccount = selectedAccount,
            onClick = {
                selectedAccount = AccountType.SAVING
                val intent = Intent(context, EnterPinActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            }
        )
        AccountItem(
            accountType = AccountType.CURRENT,
            selectedAccount = selectedAccount,
            onClick = {
                selectedAccount = AccountType.CURRENT
                val intent = Intent(context, EnterPinActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            }
        )
        AccountItem(
            accountType = AccountType.FIXED,
            selectedAccount = selectedAccount,
            onClick = {
                selectedAccount = AccountType.FIXED
                val intent = Intent(context, EnterPinActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            }
        )
    }
}

@Composable
fun AccountItem(
    accountType: AccountType,
    selectedAccount: AccountType,
    onClick: () -> Unit
) {
    val isActive = accountType == selectedAccount
    val accountCardImage = if (isActive) {
        painterResource(id = R.drawable.credit_card_1)
    } else {
        painterResource(id = R.drawable.credit_card_2)
    }

    Column(
        modifier = Modifier
            .padding(top = 19.dp)
            .border(
                width = 1.dp,
                color = if (isActive) Color(0xFFDD0A35) else Color(0xFF555555),
                shape = RoundedCornerShape(size = 10.dp)
            )
            .width(338.54999.dp)
            .height(69.375.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
            .clickable(onClick = onClick)
    ) {
        Row() {
            Image(
                painter = accountCardImage,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 18.dp, start = 18.5.dp)
            )
            Text(
                text = accountType.title,
                modifier = Modifier.padding(top = 23.dp, start = 18.13.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                color = if (isActive) Color(0xFFDD0A35) else Color(0xFF555555)
            )
        }
    }
}

enum class AccountType(val title: String) {
    SAVING("SAVING ACCOUNT"),
    CURRENT("CURRENT ACCOUNT"),
    FIXED("FIXED ACCOUNT")
}

*/

 */

@Composable
fun TypeOfAccount(
    amount: String?,
    onBackClicked: () -> Unit
){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        CashDepositHeader(onBackClicked)
        PriceForAccount(amount)
        AccountOption(
            onClickAccountType = {
                val intent = Intent(context, InsertCardActivity::class.java)
                intent.putExtra("amount", amount)
                context.startActivity(intent)
            },
        )
    }
}
@Composable
fun CashDepositHeader(onBackClicked: () -> Unit){
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
            text = "Cash (Deposit) ",
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
fun PriceForAccount(amount: String?){
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

@OptIn(ExperimentalTextApi::class)
@Composable
fun AccountOption(onClickAccountType:() -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, start = 22.dp)
    ) {
        Text(
            text = "Account Type",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(260.dp)
                .height(17.dp)
                .padding(start = 0.dp)

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
                .clickable { onClickAccountType() }
        ) {
            Text(
                text = "Savings Account",
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
        Spacer(modifier = Modifier.height(29.dp))
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
                .clickable { onClickAccountType() }
        ) {
            Text(
                text = "Current Account",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,
                )
        }
        Spacer(modifier = Modifier.height(29.dp))
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
                .clickable { onClickAccountType() }
        ) {
            Text(
                text = "Fixed Account",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                color= Color.Black,
                )
        }

    }
}
