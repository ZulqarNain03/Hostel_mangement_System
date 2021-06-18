package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddComplaints extends AppCompatActivity {

    TextView etName4;
    TextView etCNIC4;
    TextView etMatric3;
    Button submitButton2;
    EditText etcomplaints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaints);

        etName4 = findViewById(R.id.tvname2);
        etCNIC4 = findViewById(R.id.tvCNIC2);
        etMatric3 = findViewById(R.id.tvRoom);


        submitButton2 = findViewById(R.id.submitbtn);
        etcomplaints= findViewById(R.id.tcomplaints);


        String name = getIntent().getStringExtra("name2");
        String cnic = getIntent().getStringExtra("cnic2");
        String matric = getIntent().getStringExtra("matric2");


        etName4.setText("Name:  " + name);
        etCNIC4.setText("CNIC:  " + cnic);
        etMatric3.setText("Room Detailed:  " + matric);

        submitButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                complaints_DBHelper myDB = new complaints_DBHelper(AddComplaints.this);
                myDB.addDegree(
                        name,cnic,
                        matric,

                        etcomplaints.getText().toString().trim());

                Intent intent = new Intent(AddComplaints.this, ComplaintsActivity.class);
                startActivity(intent);
            }



        });
    }

}