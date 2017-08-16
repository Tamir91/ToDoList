package com.example.tamir.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tamir.todoapp.Entities.Note;

import java.util.ArrayList;

/**
 * Created by tamir on 16/08/17.
 */

public class NoteAdapter extends BaseAdapter {

    private ArrayList<Note> _notes;
    private Context _context;

    public NoteAdapter(ArrayList<Note> notes, Context context) {
        this._notes = notes;
        this._context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listRow = inflater.inflate(R.layout.contact_list_item, viewGroup, false);
        TextView textViewNote = listRow.findViewById(R.id.editText);

        Note currentNote = _notes.get(position);
        textViewNote.setText(currentNote.get_note());
        return view;
    }
}
