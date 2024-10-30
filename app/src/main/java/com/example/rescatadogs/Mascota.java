package com.example.rescatadogs;

public class Mascota {

    private String nombre;
    private String edad;
    private String sexo;
    private String imagen;

    public Mascota(String nombre, String edad, String sexo, String imagen) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
