package com.example.composecourseyt.data.repository

import com.example.composecourseyt.R
import com.example.composecourseyt.ui.transferflow.Bank
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

class BankRepository {

    suspend fun fetchBanks(): List<Bank> {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://nigerianbanks.xyz")
            .build()

        val response = client.newCall(request).execute()

        if (!response.isSuccessful) {
            throw Exception("Failed to fetch banks")
        }

        val responseBody = response.body?.string() ?: throw Exception("Empty response body")

        val banks = mutableListOf<Bank>()
        val jsonArray = JSONArray(responseBody)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val bankName = jsonObject.getString("name")
            val logoUrl = jsonObject.getString("logoUrl") // Provide the correct field name
            // Download the logo using Picasso/Glide/Coil or any other library
            val logoResId = R.drawable.zenith_bank_logo_logo // Replace with the actual drawable resource

            banks.add(Bank(bankName, logoResId))
        }

        return banks
    }
}
