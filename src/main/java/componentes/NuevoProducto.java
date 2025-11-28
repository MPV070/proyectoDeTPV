/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package componentes;

import com.mycompany.proyectodetpv.PantPrincipal;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * Componente visual que representa un producto en la cuadrícula de productos.
 *
 * Gestiona la interacción con clic simple (añadir a ticket) y doble clic
 * (eliminar si el modo eliminación está activo). Mantiene la información básica
 * del producto (nombre, precio, ruta de imagen) y proporciona getters necesarios.
 *
 * @author mpvlm
 */
public class NuevoProducto extends javax.swing.JPanel {

    private final PantPrincipal owner;
    private final String categoriaNombre;
    private final String nombre;
    private final float precio;
    private int cantidad = 1;
    private final String rutaImagen;
    private Timer singleClickTimer;

    /**
     * Construye la representación visual de un producto.
     *
     * @param owner ventana principal que maneja el estado de la aplicación
     * @param categoriaNombre nombre de la categoría a la que pertenece el producto
     * @param nombre nombre del producto
     * @param precio precio por unidad
     * @param rutaImagen ruta a la imagen asociada (puede ser {@code null})
     */
    public NuevoProducto(PantPrincipal owner, String categoriaNombre, String nombre, float precio, String rutaImagen) {
        this.owner = owner;
        this.categoriaNombre = categoriaNombre;
        this.nombre = nombre;
        this.precio = precio;
        this.rutaImagen = rutaImagen;

        initComponents();
        lblnombre.setText(nombre);
        lblPrecio.setText(String.format("%.2f €", precio));
        cargarImagen(rutaImagen);
        this.setName(nombre);

    }

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

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    private void cargarImagen(String ruta) {
        if (ruta == null || ruta.isEmpty()) {
            return;
        }

        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                jLabelImagen.getWidth() > 0 ? jLabelImagen.getWidth() : 130,
                jLabelImagen.getHeight() > 0 ? jLabelImagen.getHeight() : 130,
                Image.SCALE_SMOOTH);
        ImageIcon iconoFinal = new ImageIcon(imagenEscalada);

        jLabelImagen.setText("");
        jLabelImagen.setIcon(iconoFinal);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        lblnombre = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblPrecio = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelImagen = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jTextPane1);

        setMaximumSize(new java.awt.Dimension(130, 130));
        setMinimumSize(new java.awt.Dimension(130, 130));
        setPreferredSize(new java.awt.Dimension(130, 130));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setMaximumSize(new java.awt.Dimension(50, 50));
        jPanel1.setMinimumSize(new java.awt.Dimension(50, 50));
        jPanel1.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        lblnombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblnombre.setText("Nombre");
        jPanel1.add(lblnombre);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setMaximumSize(new java.awt.Dimension(50, 50));
        jPanel2.setMinimumSize(new java.awt.Dimension(50, 50));
        jPanel2.setPreferredSize(new java.awt.Dimension(50, 100));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        lblPrecio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecio.setText("Precio");
        jPanel2.add(lblPrecio);

        add(jPanel2, java.awt.BorderLayout.LINE_END);

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jLabelImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImagen.setText("Imagen");
        jLabelImagen.setToolTipText("");
        jPanel3.add(jLabelImagen);

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (evt.getClickCount() == 2) {
            if (singleClickTimer != null && singleClickTimer.isRunning()) {
                singleClickTimer.stop();
            }
            if (owner != null && owner.isModoEliminacionActivo()) {
                eliminarProducto();
            } else {
                agregarProductoATicket();
            }
            return;
        }

        if (evt.getClickCount() == 1) {
            if (singleClickTimer == null) {
                singleClickTimer = new Timer(200, e -> agregarProductoATicket());
                singleClickTimer.setRepeats(false);
            }
            singleClickTimer.restart();
        }

    }//GEN-LAST:event_formMouseClicked

    private void agregarProductoATicket() {
        boolean encontrado = false;

        for (int i = 0; i < PantPrincipal.modelo.getRowCount(); i++) {
            if (PantPrincipal.modelo.getValueAt(i, 1).equals(nombre)) {
                int cantidadActual = Integer.parseInt(PantPrincipal.jTable1.getValueAt(i, 0).toString()) + 1;
                PantPrincipal.modelo.setValueAt(cantidadActual, i, 0);
                PantPrincipal.modelo.setValueAt(cantidadActual * precio, i, 3);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            PantPrincipal.modelo.addRow(new Object[]{1, nombre, precio, precio});
        }

        float total = 0;
        for (int i = 0; i < PantPrincipal.modelo.getRowCount(); i++) {
            total += Float.parseFloat(PantPrincipal.modelo.getValueAt(i, 3).toString());
        }

        PantPrincipal.lblPrecioTotal.setText(String.format("%.2f €", total));
        PantPrincipal.basePProducto.revalidate();
        PantPrincipal.basePProducto.repaint();
    }

    private void eliminarProducto() {
        if (owner != null) {
            for (java.awt.Component comp : owner.panelCategoria.getComponents()) {
                if (comp instanceof Categoria cat && categoriaNombre.equals(cat.getName())) {
                    cat.eliminarProductoPorNombre(nombre);
                    break;
                }
            }
        }

        java.awt.Container parent = getParent();
        if (parent != null) {
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
        }

        javax.swing.table.DefaultTableModel model = PantPrincipal.modelo.getModelo();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            if (String.valueOf(model.getValueAt(i, 1)).equals(nombre)) {
                model.removeRow(i);
            }
        }

        if (owner != null) {
            owner.guardarCategoriasDesdeInterfaz();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblnombre;
    // End of variables declaration//GEN-END:variables

}
