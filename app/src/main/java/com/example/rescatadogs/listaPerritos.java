package com.example.rescatadogs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class listaPerritos extends AppCompatActivity implements MascotasAdapter.OnMascotaClickListener {
    RecyclerView mascotas;
    ArrayList<Mascota> listaMascotas;
    MascotasAdapter mascotaAdapter;
    String usuario,nombre,apellido,ci,numero,email,fechaNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_mascotas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa RecyclerView
        mascotas = findViewById(R.id.rwMascotas);
        listaMascotas = new ArrayList<>();
        mascotaAdapter = new MascotasAdapter(listaMascotas, this); // Pasamos la lista y el listener
        mascotas.setLayoutManager(new LinearLayoutManager(this));
        mascotas.setAdapter(mascotaAdapter);
        usuario= getIntent().getStringExtra("usuario");
        nombre= getIntent().getStringExtra("nombre");
        Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
        apellido= getIntent().getStringExtra("apellido");
        ci= getIntent().getStringExtra("ci");
        numero= getIntent().getStringExtra("numero");
        email= getIntent().getStringExtra("email");
        fechaNacimiento= getIntent().getStringExtra("fechaNacimiento");

        obtenermascotas();
    }

    private void obtenermascotas() {
        String URL = "http://192.168.1.84:80/rescatadogs/remoto_list_mascotas.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject mascotaJson = response.getJSONObject(i);
                                Mascota mascota = new Mascota(
                                        mascotaJson.getString("nombre"),
                                        mascotaJson.getString("edad"),
                                        mascotaJson.getString("sexo"),
                                        mascotaJson.getString("image_url")
                                );
                                listaMascotas.add(mascota);
                            }
                            mascotaAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error al procesar los datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Agregar la solicitud a la cola
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onMascotaClick(Mascota mascota) {

        // Aquí puedes manejar el evento de clic en una mascota

        Intent intent = new Intent(getApplicationContext(), solicitud.class);
        intent.putExtra("nombreMascota", mascota.getNombre());
        intent.putExtra("edadMascota", mascota.getEdad());
        intent.putExtra("sexoMascota", mascota.getSexo());
        intent.putExtra("imagenMascota", mascota.getImagen());
        intent.putExtra("nombre",nombre);
        intent.putExtra("apellido",apellido);
        intent.putExtra("usuario",usuario);
        intent.putExtra("ci",ci);
        intent.putExtra("numero",numero);
        intent.putExtra("email",email);
        intent.putExtra("fechaNacimiento",fechaNacimiento);
        startActivity(intent);
    }
}