package com.example.appautenticacaojoao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appautenticacaojoao.databinding.ActivityCadastroBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        IniciaToolbar();
        binding.btnCriarConta.setOnClickListener(v -> validadados());
    }
    private void validadados(){
        String email = binding.editEmail.getText().toString().trim();
        String senha = binding.editSenha.getText().toString().trim();

        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {

                binding.progressBar2.setVisibility(View.VISIBLE);

                criarContaFirebase(email, senha);
            } else{
                Toast.makeText(this,"Informe uma senha.",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Informe um e-mail.",Toast.LENGTH_SHORT).show();
        }
    }
    private void criarContaFirebase(String email, String senha) {
        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                finish();
                startActivity(new Intent(this, MainActivity.class));
            } else {
                binding.progressBar2.setVisibility(View.GONE);
                Toast.makeText(this, "Falha na criação da conta: ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void IniciaToolbar(){
        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }
}