package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText  name,surname,marks,address,department,age;
    Button addData,saveData,update;
    private Object StringBuffer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        name = findViewById(R.id.editTextTextPersonName);
        surname = findViewById(R.id.editTextTextPersonName2);
        marks = findViewById(R.id.editTextNumber);
        address = findViewById(R.id.editTextTextPersonName3);
        department = findViewById(R.id.editTextTextPersonName4);
        age = findViewById(R.id.editTextTextPersonName5);
        addData = findViewById(R.id.button);
        saveData = findViewById(R.id.button2);



    }

    public void onClick(View v) {
        boolean isInserted = mydb.insertData(name.getText().toString(),
                surname.getText().toString(),
                marks.getText().toString(),
                address.getText().toString(),
                department.getText().toString(),
                age.getText().toString());
        if (isInserted == true)
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();


    }

    private void showMessage(String data, String toString) {
        AlertDialog d=new AlertDialog.Builder(MainActivity.this).setTitle(data).setMessage(toString).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
        d.show();
    }


    public void oncl(View view) {
        Cursor res = mydb.getAllData();
        String showMessage;
        if (res.getCount() == 0) {
            showMessage = ("Error nothing found");
            return;

        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("ID: " + res.getString(0) + "\n");
            buffer.append("NAME: " + res.getString(1) + "\n");
            buffer.append("SURNAME: " + res.getString(2) + "\n");
            buffer.append("MARKS: " + res.getString(3) + "\n");
            buffer.append("ADDRESS: " + res.getString(4) + "\n");
            buffer.append("DEPARTMENT: " + res.getString(5) + "\n");
            buffer.append("AGE: " + res.getString(6) + "\n");


        }
        showMessage("data", buffer.toString());

    }
}