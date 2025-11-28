/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectodetpv;

/**
 * Clase de arranque (launcher) mínima de la aplicación.
 *
 * Inicializa y muestra la ventana principal `PantPrincipal`.
 *
 * @author mpvlm
 */
public class ProyectoDeTPV {

    /**
     * Método main que inicia la aplicación.
     *
     * @param args argumentos de línea de comandos (no usados)
     */
    public static void main(String[] args) {
        PantPrincipal pp = new PantPrincipal();
        pp.setVisible(true);
    }
}
