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

public class RoomActivity extends AppCompatActivity {
    RecyclerView application_recyclerview;
    FloatingActionButton addButton2;


    room_DBHelper myDB;
    ArrayList<String> id,name,cnic,matric;
    Room_CustomAdapter application_customAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        application_recyclerview = findViewById(R.id.application_recyclerview);
        addButton2 = findViewById(R.id.addbtn2);

        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomActivity.this, AddRoom.class);
                startActivity(intent);

            }
        });

        myDB = new room_DBHelper(RoomActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        cnic = new ArrayList<>();
        matric = new ArrayList<>();


        storeDataInArrays();
        application_customAdapter = new Room_CustomAdapter(RoomActivity.this,this, id,name,cnic,matric);
        application_recyclerview.setAdapter(application_customAdapter);
        application_recyclerview.setLayoutManager(new LinearLayoutManager(RoomActivity.this));
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


            }
        }
    }

}