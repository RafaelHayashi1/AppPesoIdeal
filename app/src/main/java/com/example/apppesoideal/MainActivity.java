package com.example.apppesoideal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apppesoideal.dao.PacienteDAO;
import com.example.apppesoideal.modelos.Paciente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Paciente> pacientes;
    private AdaptadorPaciente adapter;
    private PacienteDAO pacienteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pacienteDAO = new PacienteDAO(this);
        pacienteDAO.Abrir();

        pacientes = new ArrayList<>(pacienteDAO.ListarPacientes());

        adapter = new AdaptadorPaciente(this, R.layout.item_paciente, pacientes);
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
        pacientes.clear();
        pacientes.addAll(pacienteDAO.ListarPacientes());
        adapter.notifyDataSetChanged();
    }
}