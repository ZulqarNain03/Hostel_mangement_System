package com.example.assignment3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Room_DetailsActivity extends AppCompatActivity {

    EditText tvName_application,tvCNIC_application;
    EditText etMatric_application;
    Button updateButton_application,deleteButton_application;
    String id,name,cnic,matric,inter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room__details);

        tvName_application = findViewById(R.id.tvName_room);
        tvCNIC_application = findViewById(R.id.tvCNIC_room);
        etMatric_application = findViewById(R.id.tvroom3);
        updateButton_application = findViewById(R.id.updatebtn3);
        deleteButton_application = findViewById(R.id.deletebtn3);

        getandSetIntentData();

        updateButton_application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_DBHelper myDB = new room_DBHelper(Room_DetailsActivity.this);
                name = tvName_application.getText().toString().trim();
                cnic = tvCNIC_application.getText().toString().trim();
                matric = etMatric_application.getText().toString().trim();
                myDB.updateData(id,name,cnic,matric);
            }
        });

        deleteButton_application.setOnClickListener(new View.OnClickListener() {
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
                getIntent().hasExtra("matric") )

        {
            //Getting Data From Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            cnic = getIntent().getStringExtra("cnic");
            matric = getIntent().getStringExtra("matric");

            // Set Data to EditTexts

            tvName_application.setText(name);
            tvCNIC_application.setText(cnic);
            etMatric_application.setText(matric);


        }else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Room Form of  " + name+ " ?");
        builder.setMessage("Are you sure you want to Delete Room Form of " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                room_DBHelper myDB = new room_DBHelper(Room_DetailsActivity.this);
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