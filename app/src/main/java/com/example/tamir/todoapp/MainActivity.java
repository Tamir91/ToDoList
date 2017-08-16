package com.example.tamir.todoapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tamir.todoapp.Entities.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageButton buttonSave;
    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList<Note> arrayListNotes = new ArrayList<>();
    private static final int NEW_NOTE = 1;
    //ArrayList<String> arrayRecycleBin = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Context context = this;
        setListeners();
        arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_expandable_list_item_1, arrayListNotes);
    }

    public void setListeners() {
        final Context context = this;
    }

    /*This method add new string view to listView*/
    public void insertDataToList(String str) {
        Context context = this;

        listView.setAdapter(arrayAdapter);
        Toast.makeText(this, "Todo was added", Toast.LENGTH_SHORT).show();
    }


    public void initViews() {
        buttonSave = (ImageButton) findViewById(R.id.buttonSave);
        listView = (ListView) findViewById(R.id.listView);
    }

    public void toAddActivity(View view) {
        Intent intent = new Intent();
        startActivityForResult(intent, NEW_NOTE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NEW_NOTE && resultCode == RESULT_OK) {
            String string;
            string = data.getExtras().getString("note");
            insertStringToList(string);
        }
    }

    private boolean insertStringToList(String string) {
        if (arrayListNotes.add(new Note(string))) {
            arrayAdapter.notifyDataSetChanged();
            return true;
        }
        return false;
    }
}
