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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class registrate extends AppCompatActivity {
    Button Registrarse;
    TextView nombreR, apellidoR, fechaNacimientoR, numeroR, ciR, correoR, usuarioR, passwordR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Registrarse = findViewById(R.id.btnRegistrarse);
        nombreR = findViewById(R.id.txtNombre);
        apellidoR = findViewById(R.id.txtApellidos);
        fechaNacimientoR = findViewById(R.id.txtFechaNacimiento);
        numeroR = findViewById(R.id.txtTelefono);
        ciR = findViewById(R.id.txtCi);
        correoR = findViewById(R.id.txtCorreo);
        usuarioR = findViewById(R.id.txtUsuarioNuevo);
        passwordR = findViewById(R.id.txtPasswordNuevo);

        Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarUsuario();
            }
        });
    }

    private void insertarUsuario() {
        // URL de la API (cambia esta URL por la de tu servidor)
        String URL = "http://192.168.1.84:80/rescatadogs/remoto_insert_usuario.php";

        // Crear una solicitud de String con método POST
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Mostrar respuesta del servidor
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                        Intent it = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(it);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Mostrar error en caso de fallo
                        Toast.makeText(getApplicationContext(), "Error al registrar: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Mapa para enviar los parámetros de los campos

                Map<String, String> params = new HashMap<>();
                params.put("nombre", nombreR.getText().toString());
                params.put("apellido", apellidoR.getText().toString());
                params.put("usuario", usuarioR.getText().toString());
                params.put("password", passwordR.getText().toString());
                params.put("numero", numeroR.getText().toString());
                params.put("ci", ciR.getText().toString());
                params.put("email", correoR.getText().toString());
                params.put("fechaNacimiento", fechaNacimientoR.getText().toString());

                return params;
            }
        };

        // Crear una cola de solicitudes y agregar la solicitud
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
