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

public class ComplaintsActivity extends AppCompatActivity {
    RecyclerView degree_recyclerview;
    FloatingActionButton addButton3;


    complaints_DBHelper myDB;
    ArrayList<String> id,name,cnic,matric,degree;
    complaints_CustomAdapter complaints_customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        degree_recyclerview = findViewById(R.id.degree_recyclerview);
        addButton3 = findViewById(R.id.addbtn3);

        addButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComplaintsActivity.this, AddComplaints.class);
                startActivity(intent);

            }
        });

        myDB = new complaints_DBHelper(ComplaintsActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        cnic = new ArrayList<>();
        matric = new ArrayList<>();
        degree = new ArrayList<>();

        storeDataInArrays();
        complaints_customAdapter = new complaints_CustomAdapter(ComplaintsActivity.this,this, id,name,cnic,matric,degree);
        degree_recyclerview.setAdapter(complaints_customAdapter);
        degree_recyclerview.setLayoutManager(new LinearLayoutManager(ComplaintsActivity.this));
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
                matric.add(cursor.getString(3));
                degree.add(cursor.getString(4));

            }
        }
    }

}