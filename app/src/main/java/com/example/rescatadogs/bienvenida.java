package com.example.rescatadogs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class bienvenida extends AppCompatActivity {
    Button Eventos,Mascotas;
    TextView verUsuario;
    String usuario,nombre,apellido,ci,numero,email,fechaNacimiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bienvenida);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        verUsuario=findViewById(R.id.txtUsuarioVer);
        usuario= getIntent().getStringExtra("usuario");
        nombre= getIntent().getStringExtra("nombre");
        apellido= getIntent().getStringExtra("apellido");
        ci= getIntent().getStringExtra("ci");
        numero= getIntent().getStringExtra("numero");
        email= getIntent().getStringExtra("email");
        fechaNacimiento= getIntent().getStringExtra("fechaNacimiento");
        verUsuario.setText(usuario);
        Eventos=findViewById(R.id.btnEventos);
        Mascotas=findViewById(R.id.btnAdopcion);


    }
    public void VerEventos(View v)
    {
        Intent Eventos=new Intent(getApplicationContext(), listaEventos.class);
        startActivity(Eventos);
    }
    public void VerMascotas(View v)
    {
        Intent Mascotas=new Intent(getApplicationContext(), listaPerritos.class);
        Mascotas.putExtra("nombre",nombre);
        Mascotas.putExtra("apellido",apellido);
        Mascotas.putExtra("usuario",usuario);
        Mascotas.putExtra("ci",ci);
        Mascotas.putExtra("numero",numero);
        Mascotas.putExtra("email",email);
        Mascotas.putExtra("fechaNacimiento",fechaNacimiento);
        startActivity(Mascotas);
    }
}