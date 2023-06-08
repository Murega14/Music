package com.example.finext

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import com.example.finext.fragments.Income
import com.example.finext.fragments.Expense
import com.example.finext.fragments.Dashboard
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExpenseDashboard()
        }

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        // Set the default fragment
        loadFragment(com.example.finext.fragments.Dashboard())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, fragment)
            .commit()
    }

    @Composable
    fun ExpenseDashboard() {
        val totalExpense = remember { mutableStateOf(500f) }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Total Expense: $${totalExpense.value}")
            Button(
                onClick = {
                    // Code to navigate to Add Expense screen
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Add Expense")
            }
            Button(
                onClick = {
                    // Code to navigate to Create Budget screen
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Create Budget")
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val selectedFragment: Fragment = when (item.itemId) {
            R.id.dashboard -> Dashboard()
            R.id.expense -> Expense()
            R.id.income -> Income()
            else -> Dashboard() // Default fragment
        }

        loadFragment(selectedFragment)
        return true
    }
}
