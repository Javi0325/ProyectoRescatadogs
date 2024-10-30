package com.example.rescatadogs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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

public class listaEventos extends AppCompatActivity implements EventosAdapter.OnEventoClickListener {

    private RecyclerView recyclerViewEventos;
    private EventosAdapter adapter;
    private ArrayList<Evento> listaEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);

        recyclerViewEventos = findViewById(R.id.rwEventos);
        recyclerViewEventos.setLayoutManager(new LinearLayoutManager(this));

        listaEventos = new ArrayList<>();
        adapter = new EventosAdapter(listaEventos, this); // 'this' implementa OnEventoClickListener
        recyclerViewEventos.setAdapter(adapter);

        obtenereventos();
    }

    private void obtenereventos() {
        String URL = "http://192.168.1.84:80/rescatadogs/remoto_list_eventos.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject eventoJson = response.getJSONObject(i);

                                String nombre = eventoJson.getString("nombre");
                                String fecha = eventoJson.getString("fecha");
                                String ubicacion = eventoJson.getString("ubicacion");
                                String descripcion = eventoJson.getString("descripcion");
                                String imageUrl = eventoJson.getString("image_url");

                                // Crear una instancia de Evento
                                Evento evento = new Evento(nombre, fecha, ubicacion, descripcion, imageUrl);
                                listaEventos.add(evento);
                            }
                            adapter.notifyDataSetChanged();
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
    public void onEventoClick(Evento evento) {
        // Pasar los datos de evento a la siguiente actividad
        Intent intent = new Intent(listaEventos.this, detalleEventos.class);
        intent.putExtra("nombre", evento.getNombre());
        intent.putExtra("fecha", evento.getFecha());
        intent.putExtra("ubicacion", evento.getUbicacion());
        intent.putExtra("descripcion", evento.getDescripcion());
        startActivity(intent);
    }
}
