package com.example.appautenticacaojoao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appautenticacaojoao.databinding.ActivityCadastroBinding;
import com.example.appautenticacaojoao.databinding.ActivityRecuperaContaBinding;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperaContaActivity extends AppCompatActivity {

    private ActivityRecuperaContaBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperaContaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        IniciaToolbar();
        mAuth = FirebaseAuth.getInstance();
        binding.btnRecuperaConta.setOnClickListener(v -> validadados());
    }

    private void validadados(){
        String email = binding.editEmail.getText().toString().trim();

        if (!email.isEmpty()) {
            binding.progressBar2.setVisibility(View.VISIBLE);
            recuperaContaFirebase(email);
        } else {
            Toast.makeText(this,"Informe um e-mail.",Toast.LENGTH_SHORT).show();
        }
    }
    private void recuperaContaFirebase(String email){
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this,"Verifique sua caixa de entrada",Toast.LENGTH_SHORT).show();
                binding.progressBar2.setVisibility(View.GONE);
            } else {
                binding.progressBar2.setVisibility(View.GONE);
                Toast.makeText(this,"P.A.U",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void IniciaToolbar(){
        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

}