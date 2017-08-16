package com.example.tamir.todoapp.Entities;

/**
 * This class for notes object. It will be insert in ArrayList.
 * Created by tamir on 16/08/17.
 */

public class Note {
    private String _note;

    public Note(String note) {
        this._note = note;
        //Time time;
    }

    public String get_note() {
        return _note;
    }

    public void set_note(String _note) {
        this._note = _note;
    }
}
