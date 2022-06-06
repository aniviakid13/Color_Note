package com.codefresher.qwew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddActivity extends AppCompatActivity {
    EditText input_title, input_content;
    ImageButton btnSave, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mapView();
        addEvent();
    }


    private void mapView() {
        input_title = findViewById(R.id.edtTitle);
        input_content = findViewById(R.id.edtContent);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);
    }

    private void addEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteDataBase noteDataBase = new NoteDataBase(AddActivity.this);
                noteDataBase.addNote(input_title.getText().toString().trim(),
                        input_content.getText().toString());

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
    }
}