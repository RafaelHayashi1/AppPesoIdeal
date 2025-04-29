package com.example.apppesoideal;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apppesoideal.dao.PacienteDAO;
import com.example.apppesoideal.modelos.Paciente;

public class DetalhePacienteActivity extends AppCompatActivity {

    private Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_paciente);

        paciente = new Paciente(
                getIntent().getStringExtra("nome"),
                getIntent().getDoubleExtra("peso", 0),
                getIntent().getDoubleExtra("altura", 0),
                getIntent().getStringExtra("sexo")
        );

        double pesoIdeal = getIntent().getDoubleExtra("pesoIdeal", 0);

        ((TextView) findViewById(R.id.textViewDetalheNome)).setText("Nome: " + paciente.getNome());
        ((TextView) findViewById(R.id.textViewDetalheSexo)).setText("Sexo: " + paciente.getSexo());
        ((TextView) findViewById(R.id.textViewDetalheAltura)).setText(String.format("Altura: %.2f", paciente.getAltura()));
        ((TextView) findViewById(R.id.textViewDetalhePeso)).setText(String.format("Peso Atual: %.1f", paciente.getPeso()));
        ((TextView) findViewById(R.id.textViewDetalhePesoIdeal)).setText(String.format("Peso Ideal: %.1f", pesoIdeal));

        findViewById(R.id.btnApagarPaciente).setOnClickListener(v -> apagarPaciente());
        findViewById(R.id.btnVoltar).setOnClickListener(v -> finish());
    }

    private void apagarPaciente() {
        PacienteDAO dao = new PacienteDAO(this);
        dao.Abrir();

        boolean apagado = dao.apagarPaciente(
                paciente.getNome(),
                paciente.getSexo(),
                paciente.getAltura(),
                paciente.getPeso()
        );

        dao.Fechar();
        Toast.makeText(this, apagado ? "Paciente apagado com sucesso!" : "Erro ao apagar paciente", Toast.LENGTH_SHORT).show();
        finish();
    }
}
