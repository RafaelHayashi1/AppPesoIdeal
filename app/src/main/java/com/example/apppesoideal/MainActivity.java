package com.example.apppesoideal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.apppesoideal.model.Paciente;
import com.example.apppesoideal.data.PacienteDbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Paciente> pacientes;
    private PacienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PacienteDbHelper dbHelper = new PacienteDbHelper(this);
        pacientes = new ArrayList<>(dbHelper.getAllPacientes());
        adapter = new PacienteAdapter(this, R.layout.item_paciente, pacientes);
        ListView listView = findViewById(R.id.listViewPacientes);
        listView.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fabAddPaciente);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdicionarPacienteActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        PacienteDbHelper dbHelper = new PacienteDbHelper(this);
        pacientes.clear();
        pacientes.addAll(dbHelper.getAllPacientes());
        adapter.notifyDataSetChanged();
    }
}