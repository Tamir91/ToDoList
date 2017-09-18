package com.example.tamir.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tamir.todoapp.Entities.Note;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

public class EditActivity extends AppCompatActivity {
    EditText editTextNote;
    Button buttonAddNote;

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
                saveDataToMemory(note);
                finish();
            }
        });
    }

    private void saveDataToMemory(String toDo) {
        Note myNote = new Note(toDo,(byte) 0);//set priority!!
        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(myNote.get_id().toString(), MODE_PRIVATE);
            outputStream.write(myNote.toString().getBytes());//This work??
            outputStream.close();

            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
            returnToActivity(toDo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnToActivity(String toDo ){
        Intent intent = getIntent();
        intent.putExtra("note", toDo);
        setResult(RESULT_OK, intent);
    }
}
