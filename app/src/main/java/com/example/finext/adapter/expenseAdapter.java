package com.example.finext.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finext.R;
import com.example.finext.model.ExpenseModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private Context context;
    private List<ExpenseModel> expenseModelList;

    public ExpenseAdapter(Context context, List<ExpenseModel> expenseModelList) {
        this.context = context;
        this.expenseModelList = expenseModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expense, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExpenseModel expenseModel = expenseModelList.get(position);

        holder.tvExpenseAmount.setText(String.format(Locale.getDefault(), "₹ %.2f", expenseModel.getAmount()));
        holder.tvExpenseJob.setText(expenseModel.getJob());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date expenseDate = new Date(expenseModel.getDate());
        String formattedDate = dateFormat.format(expenseDate);
        holder.tvExpenseDate.setText(formattedDate);
    }

    @Override
    public int getItemCount() {
        return expenseModelList.size();
    }

    public void setExpenseList(List<ExpenseModel> expenseList) {
        expenseModelList = expenseList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvExpenseJob, tvExpenseAmount, tvExpenseDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvExpenseJob = itemView.findViewById(R.id.tv_expenseJob);
            tvExpenseAmount = itemView.findViewById(R.id.tv_expenseAmount);
            tvExpenseDate = itemView.findViewById(R.id.tv_expenseDate);
        }
    }
}

