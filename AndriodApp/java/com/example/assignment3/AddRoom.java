package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddRoom extends AppCompatActivity {

    TextView etName3;
    TextView etCNIC3;
    EditText etMatric;
    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        etName3 = findViewById(R.id.tvname4);
        etCNIC3 = findViewById(R.id.tvCNIC4);
        etMatric = findViewById(R.id.tvroom4);
        submitButton = findViewById(R.id.submitbtn);
        String name = getIntent().getStringExtra("name1");
        String cnic = getIntent().getStringExtra("cnic1");
        etName3.setText("Name:  " + name);
        etCNIC3.setText("CNIC:  " + cnic);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                room_DBHelper myDB = new room_DBHelper(AddRoom.this);
                myDB.addApplication(//etName3.getText().toString().trim(),
                        name,cnic,
                        Integer.valueOf(etMatric.getText().toString().trim()));
                           //     Integer.valueOf(etInter.getText().toString().trim()));
                Intent intent = new Intent(AddRoom.this, RoomActivity.class);
                startActivity(intent);
            }


        });
    }
}