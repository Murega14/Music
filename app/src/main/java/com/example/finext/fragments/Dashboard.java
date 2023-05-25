package com.example.expensetrackersystem.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetrackersystem.DatabaseHandler;
import com.example.expensetrackersystem.DatabaseHandlerExpense;
import com.example.expensetrackersystem.R;
import com.example.expensetrackersystem.adapter.ExpenseAdapter;
import com.example.expensetrackersystem.adapter.IncomeAdapter;
import com.example.expensetrackersystem.model.ExpenseModel;
import com.example.expensetrackersystem.model.IncomeModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private RecyclerView rvIncome, rvExpense;
    private TextView tvIncome, tvExpense;

    private FloatingActionButton addFab, addIncomeFab, addExpenseFab;
    private TextView addIncomeText, addExpenseText;

    private boolean isAllFabsVisible = false;

    private IncomeAdapter incomeAdapter;
    private ExpenseAdapter expenseAdapter;

    private List<IncomeModel> incomeModelList = new ArrayList<>();
    private List<ExpenseModel> expenseModelList = new ArrayList<>();

    private DatabaseHandler databaseHandler;
    private DatabaseHandlerExpense databaseHandlerExpense;

    private String totalIncome, totalExpense;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        initViews(view);

        databaseHandler = new DatabaseHandler(getContext());
        databaseHandlerExpense = new DatabaseHandlerExpense(getContext());
        fillIncomeModel();
        fillExpenseModel();

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFabsVisibility();
            }
        });

        addIncomeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showIncomeDialog();
            }
        });

        addExpenseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExpenseDialog();
            }
        });

        return view;
    }

    private void initViews(View view) {
        rvIncome = view.findViewById(R.id.rv_income);
        rvExpense = view.findViewById(R.id.rv_expense);
        tvIncome = view.findViewById(R.id.tv_income);
        tvExpense = view.findViewById(R.id.tv_expense);
        addFab = view.findViewById(R.id.add_fab);
        addIncomeFab = view.findViewById(R.id.add_income_fab);
        addExpenseFab = view.findViewById(R.id.add_expense_fab);
        addIncomeText = view.findViewById(R.id.add_income_text);
        addExpenseText = view.findViewById(R.id.add_expense_text);
    }

    private void fillIncomeModel() {
        incomeModelList = databaseHandler.getAllIncome();

        int total = 0;
        for (IncomeModel model : incomeModelList) {
            total += Integer.parseInt(model.getAmount());
        }
        totalIncome = String.valueOf(total);
        tvIncome.setText("₹" + totalIncome);

        incomeAdapter = new IncomeAdapter(getContext(), incomeModelList);
        rvIncome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvIncome.setHasFixedSize(true);
        rvIncome.setAdapter(incomeAdapter);
    }

    private void fillExpenseModel() {
        expenseModelList = databaseHandlerExpense.getAllExpenses();

        int total = 0;
        for (ExpenseModel model : expenseModelList) {
            total += Integer.parseInt(model.getAmount());
        }
        totalExpense = String.valueOf(total);
        tvExpense.setText("₹" + totalExpense);

        expenseAdapter = new ExpenseAdapter(getContext(), expenseModelList);
        rvExpense.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvExpense.setHasFixedSize(true);
        rvExpense.setAdapter(expenseAdapter);
    }

    private void toggleFabsVisibility() {
        if (!isAllFabsVisible) {
            addIncomeFab.show();
            addExpenseFab.show();
            addExpenseText.setVisibility(View.VISIBLE);
            addIncomeText.setVisibility(View.VISIBLE);
        } else {
            addIncomeFab.hide();
            addExpenseFab.hide();
            addExpenseText.setVisibility(View.GONE);
            addIncomeText.setVisibility(View.GONE);
        }
        isAllFabsVisible = !isAllFabsVisible;
    }

    private void showIncomeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_income, null);

        EditText etIncome = customLayout.findViewById(R.id.et_income_amount);
        EditText etType = customLayout.findViewById(R.id.et_income_type);
        EditText etNote = customLayout.findViewById(R.id.et_income_note);
        Button btnSave = customLayout.findViewById(R.id.btn_income_save);
        Button btnCancel = customLayout.findViewById(R.id.btn_income_cancel);

        builder.setView(customLayout);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = etIncome.getText().toString().trim();
                String type = etType.getText().toString().trim();
                String note = etNote.getText().toString().trim();
                long date = System.currentTimeMillis();

                if (amount.isEmpty()) {
                    etIncome.setError("Empty amount");
                } else if (type.isEmpty()) {
                    etType.setError("Empty type");
                } else if (note.isEmpty()) {
                    etNote.setError("Empty note");
                } else {
                    databaseHandler.addIncome(amount, type, note, String.valueOf(date));
                    alertDialog.dismiss();
                    fillIncomeModel();
                }
            }
        });
    }

    private void showExpenseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_expense, null);

        EditText etExpense = customLayout.findViewById(R.id.et_expense_amount);
        EditText etType = customLayout.findViewById(R.id.et_expense_type);
        EditText etNote = customLayout.findViewById(R.id.et_expense_note);
        Button btnSave = customLayout.findViewById(R.id.btn_expense_save);
        Button btnCancel = customLayout.findViewById(R.id.btn_expense_cancel);

        builder.setView(customLayout);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = etExpense.getText().toString().trim();
                String type = etType.getText().toString().trim();
                String note = etNote.getText().toString().trim();
                long date = System.currentTimeMillis();

                if (amount.isEmpty()) {
                    etExpense.setError("Empty amount");
                } else if (type.isEmpty()) {
                    etType.setError("Empty type");
                } else if (note.isEmpty()) {
                    etNote.setError("Empty note");
                } else {
                    databaseHandler.addExpense(amount, type, note, String.valueOf(date));
                    alertDialog.dismiss();
                    fillExpenseModel();
                }
            }
        });
    }

    private void init(View root) {
        rvIncome = root.findViewById(R.id.rv_income);
        rvExpense = root.findViewById(R.id.rv_expense);
        tvIncome = root.findViewById(R.id.tv_income);
        tvExpense = root.findViewById(R.id.tv_expense);
        addFab = root.findViewById(R.id.add_fab);
        addIncomeFab = root.findViewById(R.id.add_income_fab);
        addExpenseFab = root.findViewById(R.id.add_expense_fab);
        addIncomeText = root.findViewById(R.id.add_income_text);
        addExpenseText = root.findViewById(R.id.add_expense_text);
        addIncomeFab.setVisibility(View.GONE);
        addExpenseFab.setVisibility(View.GONE);
        addIncomeText.setVisibility(View.GONE);
        addExpenseText.setVisibility(View.GONE);
        isAllFabsVisible = false;
    }
}
