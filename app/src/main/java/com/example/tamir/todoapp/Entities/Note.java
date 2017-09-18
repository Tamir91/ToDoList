package com.example.tamir.todoapp.Entities;

import java.util.UUID;

/**
 * This class for notes object. It will be insert in ArrayList.
 * Created by tamir on 16/08/17.
 */

public class Note {
    private String _note;
    private UUID _id;
    private byte _priority;

    public UUID get_id() {
        return _id;
    }

    public byte get_priority() {
        return _priority;
    }

    public void set_priority(byte _priority) {
        this._priority = _priority;
    }

    public Note(String note, byte priority) {
        this._note = note;
        this._id = UUID.randomUUID();
        this._priority = priority;


        //Time time;
    }

    public String get_note() {
        return _note;
    }

    public void set_note(String _note) {
        this._note = _note;
    }
}
