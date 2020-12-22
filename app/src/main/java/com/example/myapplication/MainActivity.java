package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtPassword, edtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btnLogin);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);

        btnLogin.setOnClickListener(v -> {
            if (validate()) {
                Intent intent = new Intent(this, SecondPage.class);
                intent.putExtra("username",edtUsername.getText().toString());
                startActivity(intent);
            }
        });

    }


    public Boolean validate() {
        if (edtUsername.getText().toString().isEmpty()) {
            Toast.makeText(this, "enter user name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (edtPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!edtUsername.getText().toString().equals("1000964500213524")) {
            Toast.makeText(this, "incorrect username", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!edtPassword.getText().toString().equals("0000")) {
            Toast.makeText(this, "incorrect password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}