package com.example.assignment3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Complaints_DetailsActivity extends AppCompatActivity {

    TextView etName_degree,etCNIC_degree;
    TextView etMatric_degree,etInter_degree;
   EditText complaints;
    Button updateButton_degree,deleteButton_degree;
    String id,name,cnic,matric,degree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints__details);

        etName_degree = findViewById(R.id.tvName3);
        etCNIC_degree = findViewById(R.id.tvCNIC3);
        etMatric_degree = findViewById(R.id.Tvroom2);
        updateButton_degree = findViewById(R.id.updatebtn);
        complaints=findViewById(R.id.tcomplaints);
        deleteButton_degree = findViewById(R.id.deletebtn2);

        getandSetIntentData();

        updateButton_degree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                complaints_DBHelper myDB = new complaints_DBHelper(Complaints_DetailsActivity.this);
                name = etName_degree.getText().toString().trim();
                cnic = etCNIC_degree.getText().toString().trim();
                matric = etMatric_degree.getText().toString().trim();

                degree = complaints.getText().toString().trim();
                myDB.updateData(id,name,cnic,matric,degree);
            }
        });

        deleteButton_degree.setOnClickListener(new View.OnClickListener() {
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
                getIntent().hasExtra("matric") &&
                        getIntent().hasExtra("degree"))
        {
            //Getting Data From Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            cnic = getIntent().getStringExtra("cnic");
            matric = getIntent().getStringExtra("matric");
            degree = getIntent().getStringExtra("degree");
            // Set Data to EditTexts

            etName_degree.setText(name);
            etCNIC_degree.setText(cnic);
            etMatric_degree.setText(matric);

        }else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete  complaints form of " + name+ " ?");
        builder.setMessage("Are you sure you want to Delete complaints form of " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                complaints_DBHelper myDB = new complaints_DBHelper(Complaints_DetailsActivity.this);
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