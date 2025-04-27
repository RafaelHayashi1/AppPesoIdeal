package com.example.apppesoideal;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apppesoideal.data.PacienteDbHelper;
import android.database.sqlite.SQLiteDatabase;

public class DetalhePacienteActivity extends AppCompatActivity {
    private String nome;
    private String sexo;
    private double altura;
    private double peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_paciente);
        nome = getIntent().getStringExtra("nome");
        sexo = getIntent().getStringExtra("sexo");
        altura = getIntent().getDoubleExtra("altura", 0);
        peso = getIntent().getDoubleExtra("peso", 0);
        double pesoIdeal = getIntent().getDoubleExtra("pesoIdeal", 0);
        ((TextView) findViewById(R.id.textViewDetalheNome)).setText("Nome: " + nome);
        ((TextView) findViewById(R.id.textViewDetalheSexo)).setText("Sexo: " + sexo);
        ((TextView) findViewById(R.id.textViewDetalheAltura)).setText(String.format("Altura: %.2f", altura));
        ((TextView) findViewById(R.id.textViewDetalhePeso)).setText(String.format("Peso Atual: %.1f", peso));
        ((TextView) findViewById(R.id.textViewDetalhePesoIdeal)).setText(String.format("Peso Ideal: %.1f", pesoIdeal));
        Button btnApagar = findViewById(R.id.btnApagarPaciente);
        btnApagar.setOnClickListener(v -> {
            PacienteDbHelper dbHelper = new PacienteDbHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            int deleted = db.delete("paciente", "nome=? AND sexo=? AND altura=? AND peso=?", new String[]{nome, sexo, String.valueOf(altura), String.valueOf(peso)});
            if (deleted > 0) {
                Toast.makeText(this, "Paciente apagado com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao apagar paciente", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());
    }
} 