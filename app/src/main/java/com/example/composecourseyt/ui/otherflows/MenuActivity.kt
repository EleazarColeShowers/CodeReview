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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.composecourseyt.R
import com.example.composecourseyt.ui.PurchaseActivity

//MENU PAGE
class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFECEEF0)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MenuPage()
            }
        }
    }
}

@Composable
fun MenuPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFECEEF0))
    ) {
        Column(modifier = Modifier.weight(1f)) {
            DashBoard()
            options()
        }
        BottomBar()
    }
}

//code for dashboard
@Composable
fun DashBoard(modifier: Modifier= Modifier) {

    val dashboardMenu = painterResource(id = R.drawable.dashboard_menu)
    val customColor = Color(0xFFDD0A35)
    val dropdownArrow = painterResource(id = R.drawable.dropdown_arrow)
    val searchIcon= painterResource(id = R.drawable.search_icon)
    val barCode= painterResource(id = R.drawable.bar_code_1)
    val notificationBell= painterResource(id = R.drawable.notification_bell)

    Column(
        modifier = Modifier
            .background(Color.White)
            .height(146.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .border(1.dp, Color(0xFFD1D1D1))
                    .padding(bottom = 15.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 40.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = dashboardMenu,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Dashboard",
                        fontSize = 22.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFDD0A35),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 120.dp)
                    )
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Column {
                Row {
                    Text(
                        text = "All Products",
                        fontSize = 16.sp,
                        color = Color(0xFF042E46),
                        modifier = modifier.padding(start = 24.dp, top = 14.dp)
                    )
                    Image(
                        painter = dropdownArrow,
                        contentDescription = null,
                        modifier = modifier.padding(start = 18.dp, top = 14.dp)
                    )
                }
            }
            Column(modifier = modifier.padding(start = 102.dp, top = 14.dp)) {
                Image(
                    painter = searchIcon,
                    contentDescription = null
                )
            }
            Column(modifier = modifier.padding(top = 14.dp, start = 32.dp)) {
                Image(
                    painter = barCode,
                    contentDescription = null,
                    modifier = modifier.size(24.dp)
                )
            }
            Column(modifier = modifier.padding(start = 32.dp, top = 14.dp)) {
                Image(
                    painter = notificationBell,
                    contentDescription = null,
                )
            }
        }
    }
    }
//done with dashboard,

//code for the menu to pick the type of transaction to be carried out
@Composable
fun options(modifier: Modifier = Modifier) {
    val bankIcon1 = painterResource(id = R.drawable.bank_icon1)
    val bankIcon2 = painterResource(id = R.drawable.bank_icon2)
    val bankIcon3 = painterResource(id = R.drawable.bank_icon3)
    val bankIcon4= painterResource(id = R.drawable.bank_icon4)
    val bankIcon5= painterResource(id = R.drawable.bank_icon5)
    val bankIcon6= painterResource(id = R.drawable.bank_icon6)
    val bankIcon7= painterResource(id = R.drawable.bank_icon7)
    val bankIcon8= painterResource(id = R.drawable.bank_icon8)

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
        // Handle any result if needed
    }


    Column(modifier = modifier.padding(start = 36.dp, top = 30.dp, end = 22.dp, bottom = 17.dp)) {
        Row(

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
                    .width(155.dp)
                    .height(115.dp)
                    .background(color = Color(0xFFFBFBFB), shape = RoundedCornerShape(size = 16.dp), )
                    .padding(top = 22.dp, start = 18.dp)
                    .clickable {
                        val intent = Intent(context, PurchaseActivity::class.java)
                        launcher.launch(intent)
                    }

            ) {
                // Child views.
                Image(
                    painter = bankIcon1,
                    contentDescription = null,
                    modifier = Modifier
                        .offset(x = 0.dp, y = 0.dp)
                        .padding(0.dp)
                        .width(35.dp)
                        .height(35.dp)
                        .background(
                            color = Color(0xFFFDEEEA),
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                )
                Text(
                    text = "Purchase",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF042E46),
                    modifier = Modifier
                        .width(68.dp)
                        .height(26.dp)
                )
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
                    .width(155.dp)
                    .height(115.dp)
                    .background(color = Color(0xFFFBFBFB), shape = RoundedCornerShape(size = 16.dp))
                    .padding(start = 20.dp, top = 22.dp, end = 18.dp, bottom = 22.dp)
            ) {
                // Child views.
                Image(
                    painter = bankIcon2,
                    contentDescription = null,
                    modifier = Modifier
                        .offset(x = 0.dp, y = 0.dp)
                        .padding(0.dp)
                        .width(35.dp)
                        .height(35.dp)
                        .background(
                            color = Color(0xFFE4F3F4),
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                )
                Text(
                    text = "Pre-Auth",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF042E46),
                    modifier = Modifier
                        .width(68.dp)
                        .height(26.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(17.dp)) // Add spacer with height 17.dp

        Row() {
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
                    .width(155.dp)
                    .height(115.dp)
                    .background(color = Color(0xFFFBFBFB), shape = RoundedCornerShape(size = 16.dp))
                    .padding(start = 18.dp, top = 22.dp, end = 18.dp, bottom = 22.dp)
            ) {
                // Child views.
                Image(
                    painter = bankIcon3,
                    contentDescription = null,
                    modifier = Modifier
                        .offset(x = 0.dp, y = 0.dp)
                        .padding(0.dp)
                        .width(35.dp)
                        .height(35.dp)
                        .background(
                            color = Color(0xFFD6D8D8),
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                )
                Text(
                    text = "Sale Completion",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF042E46),
                    modifier = Modifier
                        .width(200.dp)
                        .height(26.dp)
                )

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
                    .width(155.dp)
                    .height(115.dp)
                    .background(color = Color(0xFFFBFBFB), shape = RoundedCornerShape(size = 16.dp))
                    .padding(start = 20.dp, top = 22.dp, end = 18.dp, bottom = 22.dp)
            ) {
                // Child views.
                Image(
                    painter = bankIcon4,
                    contentDescription = null,
                    modifier = Modifier
                        .offset(x = 0.dp, y = 0.dp)
                        .padding(0.dp)
                        .width(35.dp)
                        .height(35.dp)
                        .background(
                            color = Color(0xFFFCF6C5),
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                )
                Text(
                    text = "Refund",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF042E46),
                    modifier = Modifier
                        .width(68.dp)
                        .height(26.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(17.dp)) // Add spacer with height 17.dp

        Row() {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .shadow(
                        elevation = 30.dp,
                        spotColor = Color(0x141B1F44),
                        ambientColor = Color(0x141B1F44)
                    )
                    .width(155.dp)
                    .height(115.dp)
                    .background(color = Color(0xFFFBFBFB), shape = RoundedCornerShape(size = 16.dp))
                    .padding(start = 20.dp, top = 22.dp, end = 18.dp, bottom = 22.dp)
            ){
                Image(
                    painter = bankIcon5,
                    contentDescription = null,
                    modifier = Modifier
                        .offset(x = 0.dp, y = 0.dp)
                        .padding(0.dp)
                        .width(35.dp)
                        .height(35.dp)
                        .background(
                            color = Color(0xFFD6F3B4),
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                )
                Text(
                    text = "Cashback",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF042E46),
                    modifier = Modifier
                        .width(80.dp)
                        .height(26.dp)
                )
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
                    .width(155.dp)
                    .height(115.dp)
                    .background(color = Color(0xFFFBFBFB), shape = RoundedCornerShape(size = 16.dp))
                    .padding(start = 20.dp, top = 22.dp, end = 18.dp, bottom = 22.dp)
            ) {
                // Child views.
                Image(
                    painter = bankIcon6,
                    contentDescription = null,
                    modifier = Modifier
                        .offset(x = 0.dp, y = 0.dp)
                        .padding(0.dp)
                        .width(35.dp)
                        .height(35.dp)
                        .background(
                            color = Color(0xFFDCF7F4),
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                )
                Text(
                    text = "Cash Advance",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF042E46),
                    modifier = Modifier
                        .width(200.dp)
                        .height(26.dp)
                )
            }

        }
        Spacer(modifier = Modifier.height(17.dp))
        Row() {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .shadow(
                        elevation = 30.dp,
                        spotColor = Color(0x141B1F44),
                        ambientColor = Color(0x141B1F44)
                    )
                    .width(155.dp)
                    .height(115.dp)
                    .background(color = Color(0xFFFBFBFB), shape = RoundedCornerShape(size = 16.dp))
                    .padding(start = 20.dp, top = 22.dp, end = 18.dp, bottom = 22.dp)
            ){
                Image(
                    painter = bankIcon7,
                    contentDescription = null,
                    modifier = Modifier
                        .offset(x = 0.dp, y = 0.dp)
                        .padding(0.dp)
                        .width(35.dp)
                        .height(35.dp)
                        .background(
                            color = Color(0xFFB6C0FA),
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                )
                Text(
                    text = "Cash Deposit",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF042E46),
                    modifier = Modifier
                        .width(200.dp)
                        .height(26.dp)
                )
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
                    .width(155.dp)
                    .height(115.dp)
                    .background(color = Color(0xFFFBFBFB), shape = RoundedCornerShape(size = 16.dp))
                    .padding(start = 20.dp, top = 22.dp, end = 18.dp, bottom = 22.dp)
            ){
                Image(
                    painter = bankIcon8,
                    contentDescription = null,
                    modifier = Modifier
                        .offset(x = 0.dp, y = 0.dp)
                        .padding(0.dp)
                        .width(35.dp)
                        .height(35.dp)
                        .background(
                            color = Color(0xFFCB8DD6),
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                )
                Text(
                    text = "Cash Withdrawal",
                    fontSize = 15.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF042E46),
                    modifier = Modifier
                        .width(200.dp)
                        .height(26.dp)
                )
            }
        }
    }
}
//done with menu
//code for the bottom bar
@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    val menuIcon= painterResource(id = R.drawable.menu_icon)
    val walletIcon= painterResource(id = R.drawable.inactive_wallet)
    val eodIcon= painterResource(id = R.drawable.inactive_eod)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(87.dp)
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 40.dp,
                        spotColor = Color(0x08000000),
                        ambientColor = Color(0x08000000)
                    )
                    .border(
                        width = 0.2.dp,
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    )

                    .height(72.dp)
            ) {
                // Content of the BottomBar
                Image(
                    painter = menuIcon,
                    contentDescription = null,
                    modifier= modifier

                        .padding(start = 37.dp, top = 25.dp)
                        .width(28.dp)
                        .height(28.dp)
                )
                Image(
                    painter = walletIcon,
                    contentDescription = null,
                    modifier= modifier
                        .padding(start = 99.dp, top = 25.dp)
                        .width(28.dp)
                        .height(28.dp)
                )
                Image(
                    painter = eodIcon,
                    contentDescription = null,
                    modifier= modifier
                        .padding(start = 99.dp, top=25.dp)
                        .width(27.99989.dp)
                        .height(28.dp)
                )
            }
        }
    }