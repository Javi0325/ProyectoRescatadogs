package com.example.rescatadogs;

public class Evento {
    private String nombre;
    private String fecha;
    private String ubicacion;
    private String descripcion;
    private String imageUrl;

    public Evento(String nombre, String fecha, String ubicacion, String descripcion, String imageUrl) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.imageUrl = imageUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
