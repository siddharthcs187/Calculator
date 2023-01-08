package com.example.calculator;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> calc;
    DBHelper DB;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        DB = new DBHelper(this);
        calc = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);

        adapter = new MyAdapter(this, calc);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayhistory();
    }

    public void deleteOnClick(View view){
        DB.deleteData();
        displayhistory();
    }

    public void backOnClick(View view){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void displayhistory() {
        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0){
            Toast.makeText(History.this, "No History", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            while (cursor.moveToNext()){
                calc.add(cursor.getString(0));
            }
        }
    }
}