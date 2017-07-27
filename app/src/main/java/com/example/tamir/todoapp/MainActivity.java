package com.example.tamir.todoapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonSave, buttonUndo, buttonSettings;
    EditText editText;
    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayListString = new ArrayList<>();
    ArrayList<String> arrayRecycleBin = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setListeners();
    }

    public void setListeners() {
        final Context context = this;
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDataToList(editText.getText().toString());
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MenuActivity.class);
                startActivity(intent);
            }
        });

        buttonUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!arrayRecycleBin.isEmpty()) {
                    insertDataToList(arrayRecycleBin.get(arrayRecycleBin.size() - 1));
                    arrayRecycleBin.remove(arrayRecycleBin.size() - 1);
                } else
                    Toast.makeText(context, "The stack is empty", Toast.LENGTH_SHORT).show();
            }
        });
/*Click button for delete*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, int i, long l) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Delete");
                alertDialog.setMessage("Are you sure you want delete it?");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //here code of deleting;
                        String item = ((TextView) view).getText().toString();
                        arrayListString.remove(item);
                        arrayRecycleBin.add(item);
                        arrayAdapter.notifyDataSetChanged();
                        buttonUndo.setVisibility(View.VISIBLE);
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.show();
            }
        });

        /*Long clicking for edit it */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = ((TextView) view).getText().toString();
                editText.setText(item);
                arrayListString.remove(item);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    /*This method add new string view to listView*/
    public void insertDataToList(String str) {
        Context context = this;
        editText.setText("");
        arrayListString.add(str);
        arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_expandable_list_item_1, arrayListString);
        listView.setAdapter(arrayAdapter);
        Toast.makeText(this, "Todo was added", Toast.LENGTH_SHORT).show();
    }

    public void initViews() {
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonUndo = (Button) findViewById(R.id.buttonUndo);
        buttonSettings = (Button) findViewById(R.id.buttonSettings);
        editText = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.listView);
    }
}
