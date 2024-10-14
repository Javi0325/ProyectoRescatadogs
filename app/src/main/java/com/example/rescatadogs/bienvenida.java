package com.example.rescatadogs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class bienvenida extends AppCompatActivity {
    Button Eventos,Mascotas;
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
        startActivity(Mascotas);
    }
}