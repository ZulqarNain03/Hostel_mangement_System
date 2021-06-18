package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddApplicant extends AppCompatActivity {

    EditText etName;
    EditText etCNIC;
    EditText etEmail;
    EditText etPhone;
    Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_applicant);

        etName = findViewById(R.id.tvname1);
        etCNIC = findViewById(R.id.tvCNIC1);
        etEmail = findViewById(R.id.tvEmail1);
        etPhone = findViewById(R.id.tvPhone1);
        addButton = findViewById(R.id.addbtn1);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applicant_DBHelper myDB = new applicant_DBHelper(AddApplicant.this);
                myDB.addApplicant(etName.getText().toString().trim(),
                        etCNIC.getText().toString().trim(),
                        etEmail.getText().toString().trim(),
                        etPhone.getText().toString().trim());
            }
        });
    }
}