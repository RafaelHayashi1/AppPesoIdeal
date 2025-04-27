package com.example.apppesoideal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.apppesoideal.model.Paciente;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Exemplo de dados
        ArrayList<Paciente> pacientes = new ArrayList<>();
        pacientes.add(new Paciente("Fulano", 70.5, 1.75, "M"));
        pacientes.add(new Paciente("Ciclana", 65.0, 1.68, "F"));

        // Configurar o adapter
        PacienteAdapter adapter = new PacienteAdapter(this, R.layout.item_paciente, pacientes);

        ListView listView = findViewById(R.id.listViewPacientes);
        listView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabAddPaciente);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdicionarPacienteActivity.class);
            startActivity(intent);
        });
    }
}