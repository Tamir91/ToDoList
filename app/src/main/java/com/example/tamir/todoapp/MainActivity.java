package com.example.tamir.todoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tamir.todoapp.Entities.Note;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    ImageButton buttonAdd;
    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayList = new ArrayList<>();
    FileInputStream fileInputStream = null;
    private static final int NEW_NOTE = 1;
    //ArrayList<String> arrayRecycleBin = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Context context = this;
        setListeners();
        arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_expandable_list_item_1, arrayList);
        insertDataToList();
    }

    /*This method add new string view to listView*/
    public void insertDataToList() {
        listView.setAdapter(arrayAdapter);
    }

    public void setListeners() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditActivity.class);
                startActivityForResult(intent, NEW_NOTE);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String fileName = ((TextView) view).getText().toString();
                arrayList.remove(fileName);
                arrayAdapter.notifyDataSetChanged();
                deleteFile(fileName);
            }
        });
    }



    public void initViews() {
        buttonAdd = (ImageButton) findViewById(R.id.buttonAdd);
        listView = (ListView) findViewById(R.id.listView);
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        if (arrayList.add(string)) {
            arrayAdapter.notifyDataSetChanged();
            return true;
        }
        return false;
    }


    private void loadData() throws IOException {

        fileInputStream = openFileInput("ToDoList" + UUID.randomUUID());

        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        inputStreamReader.close();
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
    }
}
