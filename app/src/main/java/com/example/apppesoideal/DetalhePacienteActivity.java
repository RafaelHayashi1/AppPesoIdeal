package com.example.apppesoideal;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalhePacienteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_paciente);

        // Receber dados do paciente via Intent
        String nome = getIntent().getStringExtra("nome");
        String sexo = getIntent().getStringExtra("sexo");
        double altura = getIntent().getDoubleExtra("altura", 0);
        double peso = getIntent().getDoubleExtra("peso", 0);
        double pesoIdeal = getIntent().getDoubleExtra("pesoIdeal", 0);

        ((TextView) findViewById(R.id.textViewDetalheNome)).setText("Nome: " + nome);
        ((TextView) findViewById(R.id.textViewDetalheSexo)).setText("Sexo: " + sexo);
        ((TextView) findViewById(R.id.textViewDetalheAltura)).setText(String.format("Altura: %.2f", altura));
        ((TextView) findViewById(R.id.textViewDetalhePeso)).setText(String.format("Peso Atual: %.1f", peso));
        ((TextView) findViewById(R.id.textViewDetalhePesoIdeal)).setText(String.format("Peso Ideal: %.1f", pesoIdeal));
    }
} 