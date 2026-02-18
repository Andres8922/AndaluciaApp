package com.example.andaluciaapp;

public class Personaje {
    private int imagenRes; // ID del recurso (R.drawable.nombre)
    private String nombre;
    private String descripcion;

    public Personaje(int imagenRes, String nombre, String descripcion) {
        this.imagenRes = imagenRes;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters
    public int getImagenRes() { return imagenRes; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
}
