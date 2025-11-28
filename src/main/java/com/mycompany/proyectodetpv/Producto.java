/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodetpv;

/**
 * Modelo sencillo que representa un producto con nombre, precio, cantidad y ruta de imagen.
 *
 * Esta clase es utilizada por las categorías para almacenar información lógica de los
 * productos mostrados en la interfaz.
 *
 * @author mpvlm
 */
public class Producto {

    private String nombre;
    private float precio;
    private int cantidad;
    private String rutaImagen;

    /**
     * Crea un producto con sus propiedades básicas.
     *
     * @param nombre nombre del producto
     * @param precio precio por unidad
     * @param cantidad cantidad inicial
     * @param rutaImagen ruta (local) a la imagen asociada, puede ser {@code null} o cadena vacía
     */
    public Producto(String nombre, float precio, int cantidad, String rutaImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.rutaImagen = rutaImagen;
    }

    // Getters
    /**
     * Obtiene el nombre del producto.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio por unidad.
     *
     * @return precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Obtiene la cantidad del producto.
     *
     * @return cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Obtiene la ruta de la imagen (puede ser {@code null} o vacía).
     *
     * @return ruta de la imagen
     */
    public String getRutaImagen() {
        return rutaImagen;
    }

    // Setters
    /**
     * Establece el nombre del producto.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el precio unitario del producto.
     *
     * @param precio nuevo precio
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * Establece la cantidad del producto.
     *
     * @param cantidad nueva cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Establece la ruta de la imagen asociada.
     *
     * @param rutaImagen ruta local al fichero de imagen
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

}
