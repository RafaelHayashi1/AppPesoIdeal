package com.example.apppesoideal.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.apppesoideal.model.Paciente;
import java.util.ArrayList;
import java.util.List;

public class PacienteDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pacientes.db";
    private static final int DATABASE_VERSION = 1;

    public PacienteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS paciente");
        onCreate(db);
    }

    public List<Paciente> getAllPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nome, peso, altura, sexo FROM paciente", null);
        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(0);
                double peso = cursor.getDouble(1);
                double altura = cursor.getDouble(2);
                String sexo = cursor.getString(3);
                pacientes.add(new Paciente(nome, peso, altura, sexo));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return pacientes;
    }
} 