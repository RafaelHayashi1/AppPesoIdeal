package com.example.apppesoideal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.example.apppesoideal.data.PacienteDbHelper;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

public class AdicionarPacienteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_paciente);

        EditText editNome = findViewById(R.id.editTextNome);
        EditText editAltura = findViewById(R.id.editTextAltura);
        EditText editPeso = findViewById(R.id.editTextPeso);
        Button btnSalvar = findViewById(R.id.btnSalvarPaciente);
        Spinner spinnerSexo = findViewById(R.id.spinnerSexo);

        ArrayAdapter<String> sexoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"M", "F"});
        sexoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSexo.setAdapter(sexoAdapter);

        btnSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String sexo = spinnerSexo.getSelectedItem().toString();
            String alturaStr = editAltura.getText().toString().trim();
            String pesoStr = editPeso.getText().toString().trim();

            if (nome.isEmpty() || sexo.isEmpty() || alturaStr.isEmpty() || pesoStr.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            double altura = Double.parseDouble(alturaStr);
            double peso = Double.parseDouble(pesoStr);

            PacienteDbHelper dbHelper = new PacienteDbHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nome", nome);
            values.put("sexo", sexo);
            values.put("altura", altura);
            values.put("peso", peso);

            long newRowId = db.insert("paciente", null, values);
            if (newRowId != -1) {
                Toast.makeText(this, "Paciente salvo com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro ao salvar paciente", Toast.LENGTH_SHORT).show();
            }
        });
    }
} 