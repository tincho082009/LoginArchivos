package com.example.loginarchivos.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginarchivos.R;
import com.example.loginarchivos.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail, etContra;
    private Button btnLogin, btnRegistrar;
    private MainViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);
        configView();
        vm.getActivadorIntent().observe(this, new Observer<Boolean>(){
            @Override
            public void onChanged(Boolean bool) {
                Intent i = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(i);
            }
        });

    }
    public void configView(){
        etEmail = findViewById(R.id.etEmailLogin);
        etContra = findViewById(R.id.etContraLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.btnRegistrarse);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.validar(etEmail.getText().toString(), etContra.getText().toString());
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.registrar();
            }
        });

    }
}
