/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodetpv;

/**
 *
 * @author mpvlm
 */
public class Producto {

    private String nombre;
    private float precio;
    private int cantidad;
    private String rutaImagen;

    public Producto(String nombre, float precio, int cantidad, String rutaImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.rutaImagen = rutaImagen;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

}
