package com.example.finext.models

data class BudgetModel(
    var expenseId: String? = null,
    var budgetValue: String? = null,
    var selectedBudgetPeriod: String? = null
)
