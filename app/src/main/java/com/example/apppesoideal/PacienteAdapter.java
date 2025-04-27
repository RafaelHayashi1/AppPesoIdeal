package com.example.apppesoideal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.apppesoideal.model.Paciente;

import java.util.List;

public class PacienteAdapter extends ArrayAdapter<Paciente> {
    
    private int resourceLayout;

    public PacienteAdapter(Context context, int resource, List<Paciente> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(resourceLayout, parent, false);
        }

        Paciente paciente = getItem(position);

        TextView nome = convertView.findViewById(R.id.textViewNome);
        TextView pesoAtual = convertView.findViewById(R.id.textViewPesoAtual);
        TextView pesoIdeal = convertView.findViewById(R.id.textViewPesoIdeal);

        nome.setText(paciente.getNome());
        pesoAtual.setText(String.format("%.1f", paciente.getPeso()));
        double pesoIdealValor = paciente.calcularPesoIdeal();
        pesoIdeal.setText(String.format("%.1f", pesoIdealValor));
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DetalhePacienteActivity.class);
            intent.putExtra("nome", paciente.getNome());
            intent.putExtra("sexo", paciente.getSexo());
            intent.putExtra("altura", paciente.getAltura());
            intent.putExtra("peso", paciente.getPeso());
            intent.putExtra("pesoIdeal", paciente.calcularPesoIdeal());
            getContext().startActivity(intent);
        });

        return convertView;
    }
} 