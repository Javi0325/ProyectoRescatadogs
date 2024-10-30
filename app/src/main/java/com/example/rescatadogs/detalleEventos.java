package com.example.rescatadogs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class detalleEventos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_eventos);

        // Habilita la adaptación para sistemas Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener referencias a los elementos de UI
        TextView tituloEvento = findViewById(R.id.txtTitulo);
        TextView detalleEvento = findViewById(R.id.txtDescripcion);
        ImageView imagenEvento = findViewById(R.id.imgEvento);
        Button btnRegresar = findViewById(R.id.btnRegresarEventos);

        // Recibir los datos del Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String fecha = intent.getStringExtra("fecha");
        String ubicacion = intent.getStringExtra("ubicacion");
        String descripcion = intent.getStringExtra("descripcion");
        String imageUrl = intent.getStringExtra("image_url");

        // Colocar los datos en los TextView
        tituloEvento.setText(nombre);
        detalleEvento.setText("Fecha: " + fecha + "\nUbicación: " + ubicacion + "\n\nDescripción: " + descripcion);

        // Cargar la imagen usando Picasso (para URL de imagen)
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(imagenEvento);
        } else {
            imagenEvento.setImageResource(R.drawable.ic_launcher_foreground); // Imagen por defecto
        }

        // Botón para regresar
        btnRegresar.setOnClickListener(v -> finish()); // Cierra la actividad actual y regresa a la anterior
    }
}
