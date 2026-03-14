package com.example.organizadordeestudos;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;

public class CadastroActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private int idEstudoEdicao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        dbHelper = new DatabaseHelper(this);

        EditText etDisciplina = findViewById(R.id.etDisciplina);
        EditText etHorario = findViewById(R.id.etHorario);
        Button btnSalvar = findViewById(R.id.btnSalvar);
        ImageButton btnVoltar = findViewById(R.id.btnVoltar);

        // Ação da Seta de Voltar
        btnVoltar.setOnClickListener(v -> finish());

        aplicarMascaraHoraDupla(etHorario);

        if (getIntent().hasExtra("ID_ESTUDO")) {
            idEstudoEdicao = getIntent().getIntExtra("ID_ESTUDO", -1);
            etDisciplina.setText(getIntent().getStringExtra("DISCIPLINA"));
            etHorario.setText(getIntent().getStringExtra("HORARIO"));
            btnSalvar.setText("Atualizar Atividade");
        }

        btnSalvar.setOnClickListener(v -> {
            String disciplina = etDisciplina.getText().toString().trim();
            String horario = etHorario.getText().toString().trim();

            // Validação agora exige os 13 caracteres (HH:mm - HH:mm)
            if (disciplina.isEmpty() || horario.length() < 13) {
                Snackbar.make(v, "Preencha a disciplina e o horário completo (HH:mm - HH:mm).", Snackbar.LENGTH_LONG)
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

    // Máscara nova para "HH:mm - HH:mm"
    private void aplicarMascaraHoraDupla(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString().replaceAll("[^\\d]", ""); // Pega só os números
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                isUpdating = true;

                StringBuilder formatado = new StringBuilder();

                for (int i = 0; i < str.length(); i++) {
                    if (i == 2) {
                        formatado.append(":"); // HH:
                    } else if (i == 4) {
                        formatado.append(" - "); // HH:mm -
                    } else if (i == 6) {
                        formatado.append(":"); // HH:mm - HH:
                    }
                    formatado.append(str.charAt(i));
                }

                // Limita ao tamanho máximo de HH:mm - HH:mm (13 caracteres contando espaços e símbolos)
                if (formatado.length() > 13) {
                    formatado.setLength(13);
                }

                editText.setText(formatado.toString());
                editText.setSelection(formatado.length());
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }
}