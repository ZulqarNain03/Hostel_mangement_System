package com.example.assignment3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Applicant_DetailsActivity extends AppCompatActivity {

    EditText etName2,etCNIC2,etEmail2,etPhone2;
    Button updateButton,deleteButton_applicant;
    String id,name,cnic,email,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant__details);

        etName2 = findViewById(R.id.tvname1);
        etCNIC2 = findViewById(R.id.tvCNIC1);
        etEmail2 = findViewById(R.id.tvEmail1);
        etPhone2 = findViewById(R.id.tvPhone1);
        updateButton = findViewById(R.id.addbtn1);
        deleteButton_applicant = findViewById(R.id.deletebtn);

        getandSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                applicant_DBHelper myDB = new applicant_DBHelper(Applicant_DetailsActivity.this);
                name = etName2.getText().toString().trim();
                cnic = etCNIC2.getText().toString().trim();
                email = etEmail2.getText().toString().trim();
                phone = etPhone2.getText().toString().trim();
                myDB.updateData(id,name,cnic,email,phone);
            }
        });

        deleteButton_applicant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });



    }
    void getandSetIntentData(){
        if(getIntent().hasExtra("id") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("cnic") &&
                getIntent().hasExtra("email") &&
                getIntent().hasExtra("phone"))
        {
            //Getting Data From Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            cnic = getIntent().getStringExtra("cnic");
            email = getIntent().getStringExtra("email");
            phone = getIntent().getStringExtra("phone");
            // Set Data to EditTexts
            etName2.setText(name);
            etCNIC2.setText(cnic);
            etEmail2.setText(email);
            etPhone2.setText(phone);

        }else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Data of " + name+ " ?");
        builder.setMessage("Are you sure you want to delete Data of " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                applicant_DBHelper myDB = new applicant_DBHelper(Applicant_DetailsActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}