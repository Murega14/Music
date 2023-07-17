package com.example.finext.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.finext.R
import com.example.finext.models.ExpenseModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ExpenseFragment : Fragment() {

    private lateinit var label: EditText
    private lateinit var amount: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_expense_fragment, container, false)

        label = view.findViewById(R.id.label)
        amount = view.findViewById(R.id.amount)
        btnSaveData = view.findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Expenses")

        btnSaveData.setOnClickListener {
            saveEmployeeData()
        }

        return view
    }

    private fun saveEmployeeData() {
        val labelValue = label.text.toString()
        val amountValue = amount.text.toString().toDoubleOrNull()

        if (labelValue.isEmpty()) {
            label.error = "Please enter a category"
            return
        }
        if (amountValue == null) {
            amount.error = "Please enter a valid amount"
            return
        }

        val expenseId = dbRef.push().key!!

        val expense = ExpenseModel(expenseId, labelValue, amountValue)

        dbRef.child(expenseId).setValue(expense)
            .addOnCompleteListener {
                Toast.makeText(requireContext(), "Data inserted successfully", Toast.LENGTH_LONG).show()

                label.text.clear()
                amount.text.clear()
            }
            .addOnFailureListener { err ->
                Toast.makeText(requireContext(), "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
