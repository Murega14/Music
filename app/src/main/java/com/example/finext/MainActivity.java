`
        setContent {
    ExpenseDashboard(
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
)
}

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView?.setOnNavigationItemSelectedListener(this)

        // Set the default fragment
        loadFragment(Dashboard())
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flFragment, fragment)
        fragmentTransaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val selectedFragment: Fragment? = when (item.itemId) {
            R.id.dashboard -> Dashboard()
            R.id.expense -> Expense()
            R.id.income -> Income()
            else -> null
        }

        selectedFragment?.let {
            loadFragment(it)
            return true
        }
        return false
    }
}
