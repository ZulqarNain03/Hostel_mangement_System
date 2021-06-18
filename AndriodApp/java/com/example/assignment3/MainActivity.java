package com.example.assignment3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView applicant_recyclerview;
    FloatingActionButton addButton;


    applicant_DBHelper myDB;
    ArrayList<String> id,name,cnic,email,phone;
    applicant_CustomAdapter applicant_customAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applicant_recyclerview = findViewById(R.id.application_recyclerview);
        addButton = findViewById(R.id.addbtn1);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddApplicant.class);
                startActivity(intent);

            }
        });

        myDB = new applicant_DBHelper(MainActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        cnic = new ArrayList<>();
        email = new ArrayList<>();
        phone = new ArrayList<>();

        storeDataInArrays();
        applicant_customAdapter = new applicant_CustomAdapter(MainActivity.this,this, id,name,cnic,email,phone);
        applicant_recyclerview.setAdapter(applicant_customAdapter);
        applicant_recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            recreate();
        }
    }

    void storeDataInArrays(){

        Cursor cursor = myDB.readAllData();

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                cnic.add(cursor.getString(2));
                email.add(cursor.getString(3));
                phone.add(cursor.getString(4));

            }
        }
    }

}