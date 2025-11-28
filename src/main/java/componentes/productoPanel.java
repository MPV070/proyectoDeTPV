/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package componentes;

import componentes.NuevoProducto;

/**
 * Panel que contiene visualmente los productos de una categoría.
 *
 * Proporciona un área desplazable con una cuadrícula de productos y métodos
 * para añadir productos visuales al panel.
 *
 * @author mpvlm
 */
public class productoPanel extends javax.swing.JPanel {

    private String nombre;

    /**
     * Crea un nuevo panel de productos asociado a un nombre de categoría.
     *
     * @param nombre nombre de la categoría que identifica este panel
     */
    public productoPanel(String nombre) {
        initComponents();
        this.nombre = nombre;
        this.setName(nombre); // útil para identificar el panel
    }

    /**
     * Añade un componente visual `NuevoProducto` a este panel.
     *
     * @param producto instancia visual del producto a añadir
     */
    public void añadirProducto(NuevoProducto producto) {
        jPanel2.add(producto);   // añadimos la instancia, no la clase
        jPanel2.revalidate();
        jPanel2.repaint();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(560, 540));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(540, 520));
        jPanel2.setLayout(new java.awt.GridLayout(0, 4, 5, 5));
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 140));

        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
