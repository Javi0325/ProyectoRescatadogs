package com.example.rescatadogs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rescatadogs.Evento;

import java.util.ArrayList;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.EventosViewHolder> {

    private ArrayList<Evento> eventos;
    private OnEventoClickListener onEventoClickListener;

    public EventosAdapter(ArrayList<Evento> eventos, OnEventoClickListener onEventoClickListener) {
        this.eventos = eventos;
        this.onEventoClickListener = onEventoClickListener;
    }

    @NonNull
    @Override
    public EventosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_eventos, parent, false);
        return new EventosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventosViewHolder holder, int position) {
        Evento evento = eventos.get(position);
        holder.bind(evento, onEventoClickListener);
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public static class EventosViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, fecha, ubicacion;

        public EventosViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombre);
            fecha = itemView.findViewById(R.id.tvFecha);
            ubicacion = itemView.findViewById(R.id.tvUbicacion);
        }

        public void bind(Evento evento, OnEventoClickListener listener) {
            nombre.setText(evento.getNombre());
            fecha.setText(evento.getFecha());
            ubicacion.setText(evento.getUbicacion());

            itemView.setOnClickListener(v -> listener.onEventoClick(evento));
        }
    }

    public interface OnEventoClickListener {
        void onEventoClick(Evento evento);
    }
}
