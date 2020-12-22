package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapters.AccountsAdapter;
import com.example.myapplication.adapters.HistoryAdapter;
import com.example.myapplication.models.AccountsModel;
import com.example.myapplication.models.History;

import java.util.ArrayList;

public class SecondPage extends AppCompatActivity implements View.OnClickListener {

    public static ArrayList<AccountsModel> arraylist = new ArrayList<>();
    ArrayList<History> historyList = new ArrayList<>();

    RecyclerView rvAccounts, rvHistory;

    LinearLayout linearHistory;

    AccountsAdapter accountsAdapter;
    HistoryAdapter historyAdapter;

    Button btnPay, btnTransfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        TextView tvUsername = findViewById(R.id.tvUsername);
        tvUsername.setText("Welcome, Sarabjit");// + getIntent().getStringExtra("username"));

        rvAccounts = findViewById(R.id.rvPayees);
        rvHistory = findViewById(R.id.rvHistory);

        btnPay = findViewById(R.id.btnPay);
        btnTransfer = findViewById(R.id.btnTransfer);

        linearHistory = findViewById(R.id.linearHistory);

        arraylist.add(new AccountsModel(1, "Axis", "101**********30", "1000"));
        arraylist.add(new AccountsModel(2, "PNB", "235**********12", "1500"));
        arraylist.add(new AccountsModel(3, "ICICI", "125*********01", "2000"));

        accountsAdapter = new AccountsAdapter(arraylist);
        rvAccounts.setAdapter(accountsAdapter);


        historyAdapter = new HistoryAdapter(historyList);
        rvHistory.setAdapter(historyAdapter);


        btnTransfer.setOnClickListener(this);
        btnPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPay: {
                startActivityForResult(new Intent(this, CustomDialogClass.class), 100);
                break;
            }
            case R.id.btnTransfer: {
                startActivityForResult(new Intent(this, TransferAmount.class), 200);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {
                accountsAdapter.notifyDataSetChanged();
                if (data.getBooleanExtra("sendToOther", false) == true) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "abc@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Amount transferred");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Your account number " + data.getStringExtra("otherAccount") + " has been credited with amount $" + data.getStringExtra("amount") + " from account number " + data.getStringExtra("myaccountNumber"));
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                }


            } else if (requestCode == 100) {
                accountsAdapter.notifyDataSetChanged();

                linearHistory.setVisibility(View.VISIBLE);

                historyList.add(new History(data.getStringExtra("subscription"), data.getStringExtra("amount")));
                historyAdapter.notifyDataSetChanged();
            }
        }

    }
}