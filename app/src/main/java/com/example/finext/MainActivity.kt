package com.example.finext

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseDashboard()
        }
    }

    @Preview
    @Composable
    fun ExpenseDashboard() {
        val totalExpense = remember { mutableStateOf(500f) }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Total Expense: $${totalExpense.value}")
            Button(
                onClick = {
                    findNavController().navigate("expenseFragment")
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Add Expense")
            }
            Button(
                onClick = {
                    findNavController().navigate("budgetFragment")
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Create Budget")
            }
            Button(
                onClick = {
                    findNavController().navigate("billPaymentFragment")
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Bill Payment")
            }
            Button(
                onClick = {
                    // Code to navigate to Monthly Insights screen
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Monthly Insights")
            }
        }
    }

    private fun findNavController(): NavController {
        return findNavController()

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
