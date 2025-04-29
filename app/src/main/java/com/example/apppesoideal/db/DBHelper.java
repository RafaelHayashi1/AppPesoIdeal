package com.example.apppesoideal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DBVersion = 1;

    public DBHelper(Context context){
        super(context, "pacientes.db", null, DBVersion );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE paciente (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome TEXT NOT NULL," +
            "sexo TEXT NOT NULL," +
            "altura REAL NOT NULL," +
            "peso REAL NOT NULL" +
            ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        Log.w("Atualizacao de BD", "V.Antiga: " +
                oldVersion + " - V.Nova: " + newVersion);
        db.execSQL("drop table if exists contatinhos");

        onCreate(db);

    }
} 