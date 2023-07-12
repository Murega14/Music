package com.example.finext

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
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
            Row {
                Button(
                    onClick = {
                        findNavController().navigate("expenseFragment")
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Add Expense",
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(5f, 5f),
                                blurRadius = 5f
                            )
                        ))
                }
            Row {
                Button(
                    onClick = {
                        findNavController().navigate("budgetFragment")
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Create Budget",
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(5f, 5f),
                                blurRadius = 5f
                            )
                        ))
                }
            }
            }
            Row {
                Button(
                    onClick = {
                        findNavController().navigate("billPaymentFragment")
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Bill Payment",
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(5f, 5f),
                                blurRadius = 5f
                            )
                        ))
                }
                Button(
                    onClick = {
                        // Code to navigate to Monthly Insights screen
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Monthly Insights",
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(5f, 5f),
                                blurRadius = 5f
                            )
                        ))
                }
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
