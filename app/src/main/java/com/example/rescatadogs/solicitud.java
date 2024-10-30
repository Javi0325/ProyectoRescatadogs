package com.example.rescatadogs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class solicitud extends AppCompatActivity {

    String usuario, nombre, apellido, ci, numero, email, fechaNacimiento;
    String nombreMascota, edadMascota, sexoMascota, imagenMascota;
    private static final String URL_UPDATE = "http://192.168.1.84:80/rescatadogs/remoto_solicitud.php"; // Cambia a la URL de tu servidor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_solicitud);

        // Configuración de diseño para evitar solapamiento con barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Recibir datos del Intent
        nombre = getIntent().getStringExtra("nombre");
        Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
        apellido = getIntent().getStringExtra("apellido");
        usuario = getIntent().getStringExtra("usuario");
        ci = getIntent().getStringExtra("ci");
        numero = getIntent().getStringExtra("numero");
        email = getIntent().getStringExtra("email");
        fechaNacimiento = getIntent().getStringExtra("fechaNacimiento");
        // Recibir datos de la mascota
        nombreMascota = getIntent().getStringExtra("nombreMascota");
        edadMascota = getIntent().getStringExtra("edadMascota");
        sexoMascota = getIntent().getStringExtra("sexoMascota");
        imagenMascota = getIntent().getStringExtra("imagenMascota");

        // Referencias de TextView y asignación de valores
        TextView txtNombreUsuario = findViewById(R.id.txtNombreUsuario);
        TextView txtNumero = findViewById(R.id.txtNumero);
        TextView txtCi = findViewById(R.id.txtCi);
        TextView txtNombrePerro = findViewById(R.id.txtNombrePerro);

        txtNombreUsuario.setText(nombre + " " + apellido); // Nombre completo del usuario
        txtNumero.setText("Número: " + numero); // Número de contacto
        txtCi.setText("CI: " + ci); // CI del usuario
        txtNombrePerro.setText("Para adoptar a " + nombreMascota); // Nombre de la mascota

        // Llama a la función para actualizar el estado del usuario
        actualizarEstadoUsuario();
    }

    private void actualizarEstadoUsuario() {
        // Crea un JSONObject con el nombre para enviarlo al servidor
        JSONObject parametros = new JSONObject();
        try {
            Toast.makeText(this, "Esta llegando aqui", Toast.LENGTH_SHORT).show();
            parametros.put("nombre", nombre);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al crear el JSON", Toast.LENGTH_SHORT).show();
            return; // Termina la función si hay un error
        }

        // Crea la solicitud POST con Volley
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                URL_UPDATE,
                parametros,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Maneja la respuesta JSON
                            String status = response.getString("status");
                            String message = response.getString("message");

                            if ("success".equals(status)) {
                                Toast.makeText(solicitud.this, "Estado actualizado correctamente", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(solicitud.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(solicitud.this, "Error de respuesta JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(solicitud.this, "Error en la conexión: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Agrega la solicitud a la cola de Volley
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void RegresarInicio(View v) {
        Intent it = new Intent(getApplicationContext(), bienvenida.class);
        startActivity(it);
    }
}
