package com.example.rescatadogs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MascotasAdapter extends RecyclerView.Adapter<MascotasAdapter.MascotasViewHolder> {

    private ArrayList<Mascota> mascotas;
    private OnMascotaClickListener onMascotaClickListener; // Cambio de nombre para seguir las convenciones

    // Constructor del adaptador
    public MascotasAdapter(ArrayList<Mascota> mascotas, OnMascotaClickListener onMascotaClickListener) {
        this.mascotas = mascotas;
        this.onMascotaClickListener = onMascotaClickListener;
    }

    @NonNull
    @Override
    public MascotasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mascotas, parent, false);
        return new MascotasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotasViewHolder holder, int position) {
        holder.bind(mascotas.get(position), onMascotaClickListener);
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotasViewHolder extends RecyclerView.ViewHolder {

        TextView nombreMascota, edadMascota, sexoMascota;
        ImageView imagenMascota;

        public MascotasViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreMascota = itemView.findViewById(R.id.tvNombre);
            edadMascota = itemView.findViewById(R.id.tvEdad);
            sexoMascota = itemView.findViewById(R.id.tvSexo);
            imagenMascota = itemView.findViewById(R.id.imgPerro);
        }

        public void bind(Mascota mascota, OnMascotaClickListener listener) {
            nombreMascota.setText(mascota.getNombre());
            edadMascota.setText(mascota.getEdad());
            sexoMascota.setText(mascota.getSexo());

            // Usar Picasso para cargar la imagen desde una URL
            Picasso.get().load(mascota.getImagen()).into(imagenMascota);

            itemView.setOnClickListener(v -> listener.onMascotaClick(mascota));
        }
    }

    public interface OnMascotaClickListener {
        void onMascotaClick(Mascota mascota);
    }
}