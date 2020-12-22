package com.example.myapplication.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.AccountsModel;

import java.util.ArrayList;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {
    private ArrayList<AccountsModel> listdata;

    // RecyclerView recyclerView;
    public AccountsAdapter(ArrayList<AccountsModel> listdata) {
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_account_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvAccountName.setText(listdata.get(position).getAccountName());
        holder.tvAccountnumber.setText(listdata.get(position).getAccountNumber());

        holder.btnInfo.setOnClickListener(v -> showBalance(holder.itemView.getContext(), listdata.get(position)));

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAccountName, tvAccountnumber;
        ImageButton btnInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvAccountName = (TextView) itemView.findViewById(R.id.tvAccountName);
            this.tvAccountnumber = (TextView) itemView.findViewById(R.id.tvAccountnumber);
            this.btnInfo = (ImageButton) itemView.findViewById(R.id.btnInfo);
        }
    }

    private void showBalance(Context context, AccountsModel data) {
        new AlertDialog.Builder(context)
                .setTitle("Account Detail")
                .setMessage("Account Name : " + data.getAccountName() + "\n" +
                        "Account Number : " + data.getAccountNumber() + "\n" +
                        "Balance : $" + data.getBalance() + "\n"
                )
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    // Continue with delete operation
                })
                .show();

    }
}
