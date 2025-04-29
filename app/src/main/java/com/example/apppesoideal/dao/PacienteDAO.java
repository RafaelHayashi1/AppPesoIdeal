package com.example.apppesoideal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apppesoideal.db.DBHelper;
import com.example.apppesoideal.modelos.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    private SQLiteDatabase db;
    private DBHelper helper;

    public PacienteDAO(Context context){
        helper = new DBHelper(context);
    }

    public void Abrir(){
        db = helper.getReadableDatabase();
    }

    public void Fechar(){
        helper.close();
    }

    public Long Inserir(Paciente p){
        ContentValues dados = new ContentValues();
        dados.put("nome", p.getNome());
        dados.put("peso", p.getPeso());
        dados.put("altura", p.getAltura());
        dados.put("sexo", p.getSexo());

        Long sqlId = db.insert("paciente",
                null, dados);

        return sqlId;
    }

    public boolean apagarPaciente(String nome, String sexo, double altura, double peso) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int apagados = db.delete(
                "paciente",
                "nome=? AND sexo=? AND altura=? AND peso=?",
                new String[]{nome, sexo, String.valueOf(altura), String.valueOf(peso)}
        );
        return apagados > 0;
    }

    public List<Paciente> ListarPacientes(){
        List<Paciente> lista = new ArrayList<Paciente>();

        String campos[] = new String[]{"nome", "peso", "altura", "sexo"};

        Cursor dados = db.query("paciente", campos,
                null, null, null,
                null, "nome");

        dados.moveToFirst();

        while (!dados.isAfterLast()){
            Paciente p = new Paciente(
                    dados.getString(0),   // nome
                    dados.getDouble(1),  // peso
                    dados.getDouble(2), // altura
                    dados.getString(3)   // sexo
            );
            lista.add(p);
            dados.moveToNext();
        }
        return lista;
    }
}