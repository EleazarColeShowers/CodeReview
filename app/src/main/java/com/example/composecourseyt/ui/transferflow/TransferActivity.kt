@file:Suppress("DEPRECATION")

package com.example.composecourseyt.ui.transferflow

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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.composecourseyt.R
import com.example.composecourseyt.data.repository.BankRepository
import com.example.composecourseyt.ui.otherflows.EnterPinActivity
import com.example.composecourseyt.ui.cashflow.Price

class TransferActivity : ComponentActivity() {
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
                TransferPage(amount, onBackClicked = { onBackPressed() })
            }

        }
    }
}

@Composable
fun TransferPage(
    amount: String?,
    onBackClicked: () -> Unit
){
//    val clickedNumber = remember { mutableStateOf("") }
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TransferHeader(onBackClicked)
        Price(amount)
        BankDetails(gradient = Brush.horizontalGradient(
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
fun TransferHeader(onBackClicked: () -> Unit){
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
        Spacer(modifier = Modifier.width(109.dp))
        Text(
            text = "Transfer",
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
fun BankDetails(gradient: Brush, onClickNext: () -> Unit){
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 28.dp, start = 22.dp)
    ){
        Text(
            text = "Recipient Account Details",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF222222),
            modifier = Modifier
                .width(260.dp)
                .height(17.dp)
                .padding(start = 0.dp)

        )
        Spacer(modifier = Modifier.height(27.dp))
        Column (
            modifier= Modifier
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .width(326.dp)
                .height(51.dp)
                .padding(start = 25.dp)
                .clickable {
                    showDialog = true

                }
            ){
            Text(
                text = "Select Bank",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFFCCCCCC),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
                )
            // Show the AlertDialog when showDialog is true
            if (showDialog) {
                Dialog(
                    onDismissRequest = {
                        showDialog = false
                    }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.White,
                    ) {
                        SelectBank()
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(21.dp))
        Column (
            modifier= Modifier
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .width(326.dp)
                .height(51.dp)
                .padding(start = 25.dp)
        ){
            Text(
                text = "Enter Account Number",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFFCCCCCC),
                textAlign = TextAlign.Center,
                letterSpacing = 0.04.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(72.dp))
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
                        .padding(top = 15.dp, start = 150.dp)
                )
            }
        }
    }
}

@Composable
fun SelectBank() {

    val returnArrow = painterResource(id = R.drawable.returnarrow)
    val searchBankName = painterResource(id = R.drawable.search_bank)
    val scope = rememberCoroutineScope()
    val bankRepository = remember { BankRepository() }
    var searchText by remember { mutableStateOf("") }
    var nigerianBanks by remember { mutableStateOf(emptyList<Bank>()) }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var filteredBanks = if (searchText.isEmpty()) {
        nigerianBanks
    } else {
        nigerianBanks.filter { it.name.contains(searchText, ignoreCase = true) }
    }

    LaunchedEffect(Unit) {
        try {
            nigerianBanks = bankRepository.fetchBanks()
        } catch (e: Exception) {
            // Handle error
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 17.5.dp)
    ) {
        // ... (your existing code for header)

        Row {
            Image(
                painter = returnArrow,
                contentDescription = null,
                modifier = Modifier
                    .padding(1.dp)
                    .width(36.dp)
                    .height(36.dp)
            )
            Text(
                text =" Select Bank",
                fontSize = 24.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF222222),
                modifier = Modifier
                    .padding(start = 43.dp)
                    .width(150.dp)
                    .height(30.dp)
            )
        }

        Spacer(modifier = Modifier.height(53.5.dp))

        // Search bar
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 6.dp,
                    spotColor = Color(0x5EC5C8CA),
                    ambientColor = Color(0x5E009FFF)
                )
                .border(
                    width = 1.dp,
                    color = Color(0x0D496779),
                    shape = RoundedCornerShape(size = 9.dp)
                )
                .width(306.dp)
                .height(56.dp)
                .padding(start = 10.dp, top = 16.dp, end = 10.dp, bottom = 16.dp)
        ) {
            Row() {
                Image(
                    painter = searchBankName,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(1.dp)
                        .width(24.dp)
                        .height(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Search text input
                BasicTextField(
                    value = searchText,
                    onValueChange = { newText ->
                        searchText = newText
                        isDropdownExpanded = true
                        filteredBanks = if (newText.isEmpty()) {
                            nigerianBanks
                        } else {
                            nigerianBanks.filter { bank ->
                                bank.name.contains(newText, ignoreCase = true)
                            }

                        }
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            // This block will be executed when the user presses "Enter" on the keyboard
                            isDropdownExpanded = false // Close the dropdown
                            // You can also perform any other actions you need here
                        }),
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }


        // List of banks
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(filteredBanks) { bank ->
                BankItem(bank)
            }
        }
    }
}

@Composable
fun BankItem(bank: Bank) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = bank.logoResId),
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = bank.name, fontSize = 16.sp, color = Color.Black)
    }
}



data class Bank(val name: String, val logoResId: Int)


