package com.example.organizadordeestudos;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;

public class CadastroActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private int idEstudoEdicao = -1; // -1 significa que é um novo cadastro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        dbHelper = new DatabaseHelper(this);

        EditText etDisciplina = findViewById(R.id.etDisciplina);
        EditText etHorario = findViewById(R.id.etHorario);
        Button btnSalvar = findViewById(R.id.btnSalvar);

        aplicarMascaraHora(etHorario);

        // Verifica se recebemos dados para Edição da MainActivity
        if (getIntent().hasExtra("ID_ESTUDO")) {
            idEstudoEdicao = getIntent().getIntExtra("ID_ESTUDO", -1);
            etDisciplina.setText(getIntent().getStringExtra("DISCIPLINA"));
            etHorario.setText(getIntent().getStringExtra("HORARIO"));
            btnSalvar.setText("Atualizar Atividade");
        }

        btnSalvar.setOnClickListener(v -> {
            String disciplina = etDisciplina.getText().toString().trim();
            String horario = etHorario.getText().toString().trim();

            // Validação simples (garante que tem a hora completa HH:mm)
            if (disciplina.isEmpty() || horario.length() < 5) {
                Snackbar.make(v, "Preencha a disciplina e a hora completa (HH:mm).", Snackbar.LENGTH_LONG)
                        .setBackgroundTint(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                        .show();
            } else {
                if (idEstudoEdicao == -1) {
                    dbHelper.adicionarEstudo(disciplina, horario);
                    Snackbar.make(v, "Salvo com sucesso!", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(ContextCompat.getColor(this, R.color.black)).show();
                } else {
                    dbHelper.atualizarEstudo(idEstudoEdicao, disciplina, horario);
                    Snackbar.make(v, "Atualizado com sucesso!", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(ContextCompat.getColor(this, R.color.black)).show();
                }

                new Handler(Looper.getMainLooper()).postDelayed(this::finish, 1000);
            }
        });
    }

    // Lógica para formatar automaticamente enquanto o usuário digita (Mascara HH:mm)
    private void aplicarMascaraHora(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString().replaceAll("[^\\d]", "");
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                isUpdating = true;

                if (str.length() > 2) {
                    str = str.substring(0, 2) + ":" + str.substring(2);
                }
                if (str.length() > 5) {
                    str = str.substring(0, 5); // Limita a 5 caracteres (00:00)
                }

                editText.setText(str);
                editText.setSelection(str.length());
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }
}