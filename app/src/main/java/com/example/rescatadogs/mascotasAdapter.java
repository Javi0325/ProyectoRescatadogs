package com.example.rescatadogs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
/*
public class DoctoresAdapter extends RecyclerView.Adapter<DoctoresAdapter.DoctoresViewHolder> {

    private ArrayList<Mascota> mascotas;
    private OnDoctorClickListener onDoctorClickListener;

    public DoctoresAdapter(ArrayList<Mascota> mascotas, OnDoctorClickListener onDoctorClickListener) {
        this.mascotas = mascotas;
        this.onDoctorClickListener = onDoctorClickListener ;
    }

    @NonNull
    @Override
    public DoctoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mascotas, parent, false);
        return new DoctoresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctoresViewHolder holder, int position) {
        holder.bind(mascotas.get(position), onDoctorClickListener);
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class DoctoresViewHolder extends RecyclerView.ViewHolder {

        private TextView nombreDoctor;

        public DoctoresViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreDoctor = itemView.findViewById(R.id.tvEspecialidades);
        }

        public void bind(Mascota mascota, OnDoctorClickListener listener) {
            nombreDoctor.setText(mascota.getNombres() + " " + mascota.getApellidos());
            itemView.setOnClickListener(v -> listener.onDoctorClick(mascota));
        }
    }

    public interface OnDoctorClickListener {
        void onDoctorClick(Mascota mascota);
    }
}
*/
