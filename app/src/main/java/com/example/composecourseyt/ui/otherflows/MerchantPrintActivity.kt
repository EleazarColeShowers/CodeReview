package com.example.composecourseyt.ui.otherflows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MerchantPrintActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val amount = intent.getStringExtra("amount")
        setContent {
            EnterPinPage(amount){
                onBackPressed()
            }
        }
    }
}