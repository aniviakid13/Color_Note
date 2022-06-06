package com.codefresher.qwew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton btnAdd, btnText, btnCheckList;
    NoteDataBase myDb;
    ArrayList<String> title, content;
    NoteAdapter noteAdapter;
    private boolean isBtnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView();
        addEvent();
    }


    private void mapView() {
        btnAdd = findViewById(R.id.btnAdd);
        btnText = findViewById(R.id.btnText);
        btnCheckList = findViewById(R.id.btnCheckList);
        recyclerView =findViewById(R.id.recyclerView);

    }

    private void addEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isBtnOpen) {
                    showMenu();
                } else {
                    closeMenu();
                }
            }
        });

        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDb = new NoteDataBase(MainActivity.this);
        title = new ArrayList<>();
        content = new ArrayList<>();

        displayData();

        noteAdapter = new NoteAdapter(MainActivity.this, title, content);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        noteAdapter.notifyDataSetChanged();
    }

    private void displayData() {
        Cursor cursor = myDb.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {

                title.add(cursor.getString(0));
                content.add(cursor.getString(1));
            }
        }
    }

    private void showMenu() {
        isBtnOpen = true;
        btnCheckList.animate().translationY(-getResources().getDimension(R.dimen.mgdp55));
        btnText.animate().translationY(-getResources().getDimension(R.dimen.mgdp105));
    }

    private void closeMenu() {
        isBtnOpen = false;
        btnText.animate().translationY(0);
        btnCheckList.animate().translationY(0);
    }

}