package com.example.tamir.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.OutputStream;
import java.util.UUID;

public class EditActivity extends AppCompatActivity {
    EditText editTextNote;
    Button buttonAddNote;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initViews();
        initListeners();
    }

    public void initViews() {
        editTextNote = (EditText) findViewById(R.id.editText);
        buttonAddNote = (Button) findViewById(R.id.buttonSave);
    }


    public void initListeners() {
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = editTextNote.getText().toString();
                saveDataToMemory("ToDoList" + UUID.randomUUID().toString(), note);
                finish();
            }
        });
    }

    private void saveDataToMemory(String nameFile, String note) {
        OutputStream outputStream = null;
        try {
            openFileOutput(nameFile, MODE_PRIVATE);
            outputStream.write(note.getBytes());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
