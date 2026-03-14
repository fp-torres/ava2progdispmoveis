package com.example.organizadordeestudos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAddActivity;
    private TextView tvEmptyState;
    private RecyclerView recyclerViewEstudos;

    private DatabaseHelper dbHelper;
    private EstudoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        btnAddActivity = findViewById(R.id.btnAddActivity);
        tvEmptyState = findViewById(R.id.tvEmptyState);
        recyclerViewEstudos = findViewById(R.id.recyclerViewEstudos);

        recyclerViewEstudos.setLayoutManager(new LinearLayoutManager(this));

        btnAddActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
        List<Estudo> lista = dbHelper.listarEstudos();

        if (lista.isEmpty()) {
            tvEmptyState.setVisibility(View.VISIBLE);
            recyclerViewEstudos.setVisibility(View.GONE);
        } else {
            tvEmptyState.setVisibility(View.GONE);
            recyclerViewEstudos.setVisibility(View.VISIBLE);

            if (adapter == null) {
                adapter = new EstudoAdapter(lista, new EstudoAdapter.OnItemClickListener() {
                    @Override
                    public void onEditClick(Estudo estudo) {
                        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                        intent.putExtra("ID_ESTUDO", estudo.getId());
                        intent.putExtra("DISCIPLINA", estudo.getDisciplina());
                        intent.putExtra("HORARIO", estudo.getHorario());
                        startActivity(intent);
                    }

                    @Override
                    public void onDeleteClick(Estudo estudo) {
                        // Implementação do Alerta de Confirmação
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Excluir Atividade")
                                .setMessage("Tem certeza que deseja apagar a disciplina '" + estudo.getDisciplina() + "'?")
                                .setPositiveButton("Sim", (dialog, which) -> {
                                    dbHelper.deletarEstudo(estudo.getId());
                                    carregarLista(); // Atualiza a tela imediatamente

                                    // Feedback visual de sucesso na exclusão
                                    Snackbar.make(findViewById(android.R.id.content), "Atividade excluída com sucesso!", Snackbar.LENGTH_SHORT)
                                            .setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.black))
                                            .show();
                                })
                                .setNegativeButton("Não", null) // Não faz nada, apenas fecha o popup
                                .show();
                    }
                });
                recyclerViewEstudos.setAdapter(adapter);
            } else {
                adapter.atualizarLista(lista);
            }
        }
    }
}