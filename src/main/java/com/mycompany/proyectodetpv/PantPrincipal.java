/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectodetpv;

import componentes.Categoria;
import componentes.NuevoProducto;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Ventana principal de la aplicación de TPV.
 *
 * Gestiona la interfaz principal, la lista de categorías y los paneles de productos,
 * el modelo de la tabla de pedido y las operaciones relacionadas con la persistencia
 * de categorías y productos en XML.
 *
 * Proporciona utilidades para añadir categorías desde diálogos y para activar
 * un modo de eliminación por doble click.
 *
 * @author mpvlm
 */
public class PantPrincipal extends javax.swing.JFrame {

    private boolean modoEliminacionActivo = false;

    /**
     * Comprueba si existe un producto en el modelo del ticket por su nombre.
     *
     * @param nombre nombre del producto a buscar
     * @return {@code true} si existe el producto en el ticket, {@code false} en caso contrario
     */
    public static boolean existeProducto(String nombre) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 1).equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    private void actualizarTotalGeneral() {
        double suma = 0.0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            try {
                suma += Double.parseDouble(String.valueOf(modelo.getValueAt(i, 3)));
            } catch (NumberFormatException ex) {
                // Si el valor no es numérico, mostramos un aviso y continuamos
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "El valor en la columna Total no es válido en la fila " + (i + 1),
                        "Error en cálculo",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
        }

        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00€");
        lblPrecioTotal.setText(df.format(suma));
    }

    /**
     * Creates new form pantPrincipal
     */
    public PantPrincipal() {

        initComponents();
        jTable1.setModel(PantPrincipal.modelo.getModelo());
        this.setLocationRelativeTo(null);

        // 1. Cargar categorías desde el XML
        List<Categoria> categorias = categoriaxml.cargarCategorias();

        // 2. Reconstruir interfaz con cada categoría y sus productos
        for (Categoria cat : categorias) {
            // Añadir la categoría al panel lateral
            agregarCategoriaDesdeDialog(cat, cat.getNombre());

            // Buscar el panel de productos asociado a esa categoría
            JPanel panel = getPanelProductosPorNombre(cat.getNombre());
            if (panel != null) {
                // Añadir cada producto visualmente
                for (Producto p : cat.getProductos()) {
                    NuevoProducto visual = new NuevoProducto(
                            this,
                            cat.getNombre(),
                            p.getNombre(),
                            p.getPrecio(),
                            p.getRutaImagen()
                    );
                    panel.add(visual);
                }
                panel.revalidate();
                panel.repaint();
            }
        }

        // 
        modelo.getModelo().addTableModelListener(e -> actualizarTotalGeneral());

        // Vincular el modelo estático a la tabla
        jTable1.setModel(modelo.getModelo());

        javax.swing.table.DefaultTableCellRenderer right = new javax.swing.table.DefaultTableCellRenderer();
        right.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(right); // Precio
        jTable1.getColumnModel().getColumn(3).setCellRenderer(right); // Total

        // 4) Escuchar cambios en el modelo para actualizar el Total por fila y el total general
        modelo.getModelo().addTableModelListener(e -> {
            int fila = e.getFirstRow();
            int col = e.getColumn();

            // Actualizar el total por fila cuando cambie Cantidad
            if (col == 0 && fila >= 0) {
                try {
                    float cantidad = Float.parseFloat(String.valueOf(modelo.getValueAt(fila, 0)));
                    float precio = Float.parseFloat(String.valueOf(modelo.getValueAt(fila, 2)));
                    modelo.setValueAt(cantidad * precio, fila, 3);
                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido en Cantidad", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    // Revertir a 1 para evitar estado inválido
                    modelo.setValueAt(1, fila, 0);
                    // Recalcular tras revertir
                    float precio = Float.parseFloat(String.valueOf(modelo.getValueAt(fila, 2)));
                    modelo.setValueAt(1 * precio, fila, 3);
                }
            }

            // Actualizar el total general en jLabel2 tras cualquier cambio
            actualizarTotalGeneral();
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnCategoria = new javax.swing.JButton();
        aniadirProducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelCategoria = new javax.swing.JPanel();
        pnlLista = new javax.swing.JPanel();
        pnlTotal = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        lblPrecioTotal = new javax.swing.JLabel();
        facturaBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        basePProducto = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelProducto = new javax.swing.JPanel();
        btnToggleEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(260, 400));
        jPanel2.setMinimumSize(new java.awt.Dimension(130, 200));
        jPanel2.setPreferredSize(new java.awt.Dimension(130, 400));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(153, 153, 255));
        jPanel5.setMinimumSize(new java.awt.Dimension(130, 100));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.Y_AXIS));

        btnCategoria.setText("Añadir Categoría");
        btnCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCategoriaMouseClicked(evt);
            }
        });
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });
        jPanel5.add(btnCategoria);

        aniadirProducto.setText("Añadir Producto");
        aniadirProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aniadirProductoActionPerformed(evt);
            }
        });
        jPanel5.add(aniadirProducto);

        btnToggleEliminar.setText("Eliminar");
        btnToggleEliminar.setToolTipText("Activa el modo de eliminación por doble click");
        btnToggleEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToggleEliminarActionPerformed(evt);
            }
        });
        jPanel5.add(btnToggleEliminar);

        jPanel2.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(130, 100));

        panelCategoria.setBackground(new java.awt.Color(204, 204, 255));
        // Do not force a fixed preferred size here so the panel can grow and the scrollpane
        // will show scrollbars correctly according to the number of categories.
        panelCategoria.setLayout(new javax.swing.BoxLayout(panelCategoria, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(panelCategoria);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        pnlLista.setBackground(new java.awt.Color(255, 255, 204));
        pnlLista.setMaximumSize(new java.awt.Dimension(260, 400));
        pnlLista.setMinimumSize(new java.awt.Dimension(140, 200));
        pnlLista.setPreferredSize(new java.awt.Dimension(180, 400));
        pnlLista.setLayout(new java.awt.BorderLayout());

        pnlTotal.setBackground(new java.awt.Color(242, 222, 250));
        pnlTotal.setPreferredSize(new java.awt.Dimension(172, 33));
        pnlTotal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotal.setText("Total:");
        pnlTotal.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblPrecioTotal.setText("0");
        pnlTotal.add(lblPrecioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 40, -1));

        facturaBtn.setText("Factura");
        facturaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                facturaBtnMouseClicked(evt);
            }
        });
        pnlTotal.add(facturaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 3, -1, 30));

        pnlLista.add(pnlTotal, java.awt.BorderLayout.PAGE_END);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        pnlLista.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel1.add(pnlLista, java.awt.BorderLayout.LINE_END);

        basePProducto.setBackground(new java.awt.Color(204, 255, 204));
        basePProducto.setMaximumSize(new java.awt.Dimension(400, 400));
        basePProducto.setMinimumSize(new java.awt.Dimension(200, 200));
        basePProducto.setPreferredSize(new java.awt.Dimension(600, 400));
        basePProducto.setLayout(new javax.swing.OverlayLayout(basePProducto));

        panelProducto.setBackground(new java.awt.Color(204, 255, 255));
        panelProducto.setLayout(new java.awt.GridBagLayout());
        jScrollPane2.setViewportView(panelProducto);

        basePProducto.add(jScrollPane2);

        jPanel1.add(basePProducto, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCategoriaMouseClicked
        FormularioCategoria dialog = new FormularioCategoria(this, true);
        dialog.setLocationRelativeTo(this); // Centra el diálogo sobre la ventana principal
        dialog.setVisible(true);

    }//GEN-LAST:event_btnCategoriaMouseClicked

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void aniadirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aniadirProductoActionPerformed
        FormularioProducto dialog = new FormularioProducto(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

    }//GEN-LAST:event_aniadirProductoActionPerformed

    private void facturaBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facturaBtnMouseClicked
        if (jTable1.getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "No hay productos en el pedido.");
            return;
        }

        // Generar nombre de archivo con fecha y hora
        String nombreArchivo = "factura_"
                + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
                + ".html";

        // Crear archivo en la carpeta del proyecto
        java.io.File destino = new java.io.File(nombreArchivo);

        try {
            HtmlExportarPedidos.exportarTablaComoHtml(jTable1, destino);
            javax.swing.JOptionPane.showMessageDialog(this, "Factura exportada:\n" + destino.getAbsolutePath());

            // Abrir en navegador (opcional)
            try {
                java.awt.Desktop.getDesktop().browse(destino.toURI());
            } catch (IOException ignored) {
            }
        } catch (java.io.IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al exportar: " + ex.getMessage(),
                    "Exportación HTML", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_facturaBtnMouseClicked

    private void btnToggleEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToggleEliminarActionPerformed
        modoEliminacionActivo = !modoEliminacionActivo;
        btnToggleEliminar.setBackground(modoEliminacionActivo ? new java.awt.Color(255, 204, 204) : javax.swing.UIManager.getColor("Button.background"));
        btnToggleEliminar.setForeground(modoEliminacionActivo ? java.awt.Color.RED : javax.swing.UIManager.getColor("Button.foreground"));
        if (modoEliminacionActivo) {
            if (!dialogoEliminacionMostrado) {
                mostrarRecordatorioEliminacion();
            }
        }
    }//GEN-LAST:event_btnToggleEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    /**
     * Añade la vista de categoría al panel lateral y crea el panel de productos asociado.
     *
     * @param categoriaComponent componente visual de la categoría (por ejemplo `componentes.Categoria`)
     * @param nombre nombre que identificará el panel de productos
     */
    public void agregarCategoriaDesdeDialog(Component categoriaComponent, String nombre) {
        // Añadir componente categoria al panel izquierdo
        // Ensure new category aligns left so it expands to the viewport width
        if (categoriaComponent instanceof javax.swing.JComponent jc) {
            jc.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
            jc.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, jc.getPreferredSize().height > 0 ? jc.getPreferredSize().height : 60));
        }
        panelCategoria.add(categoriaComponent);
        panelCategoria.add(javax.swing.Box.createVerticalStrut(10));
        panelCategoria.revalidate();
        panelCategoria.repaint();

        // Crear panel de productos
        JPanel productoPanel = new JPanel();
        productoPanel.setLayout(new GridLayout(0, 4, 10, 10));
        productoPanel.setName(nombre);
        productoPanel.setVisible(false);
        basePProducto.add(productoPanel);

        mostrarPanelProductosDe(nombre);

    }
    private JPanel getPanelProductosPorNombre(String nombreCat) {
        for (Component comp : basePProducto.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                if (panel.getName() != null && nombreCat.equals(panel.getName())) {
                    return panel;
                }
            } else if (comp instanceof javax.swing.JScrollPane) {
                javax.swing.JScrollPane scroll = (javax.swing.JScrollPane) comp;
                Component view = scroll.getViewport().getView();
                if (view instanceof JPanel) {
                    JPanel panel = (JPanel) view;
                    if (panel.getName() != null && nombreCat.equals(panel.getName())) {
                        return panel;
                    }
                }
            }
        }
        return null;
    }

    private void mostrarPanelProductosDe(String nombreCat) {
        for (Component comp : basePProducto.getComponents()) {
            if (comp instanceof javax.swing.JScrollPane) {
                javax.swing.JScrollPane scroll = (javax.swing.JScrollPane) comp;
                Component view = scroll.getViewport().getView();
                if (view instanceof javax.swing.JPanel) {
                    javax.swing.JPanel panel = (javax.swing.JPanel) view;
                    panel.setVisible(nombreCat.equals(panel.getName()));
                }
            } else if (comp instanceof javax.swing.JPanel) {
                javax.swing.JPanel panel = (javax.swing.JPanel) comp;
                if (panel.getName() != null) {
                    panel.setVisible(nombreCat.equals(panel.getName()));
                }
            }
        }
    }

    /**
     * Recolecta las instancias de `componentes.Categoria` presentes en la interfaz
     * y delega en {@link categoriaxml#guardarCategorias(java.util.List)} para
     * persistirlas en el fichero XML.
     */
    public void guardarCategoriasDesdeInterfaz() {
        List<Categoria> categorias = new ArrayList<>();

        for (Component comp : panelCategoria.getComponents()) {
            if (comp instanceof Categoria cat) {
                categorias.add(cat);
            }
        }

        categoriaxml.guardarCategorias(categorias);
    }

    private static boolean dialogoEliminacionMostrado = false;

    private void mostrarRecordatorioEliminacion() {
        if (dialogoEliminacionMostrado) {
            return;
        }
        dialogoEliminacionMostrado = true;

        javax.swing.JDialog dialogo = new javax.swing.JDialog(this, "Información", true);
        dialogo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogo.setLayout(new java.awt.BorderLayout(10, 10));

        javax.swing.JLabel mensaje = new javax.swing.JLabel("Haz doble click sobre el producto que quieras borrar mientras el modo \"Eliminar\" esté activo.", javax.swing.SwingConstants.CENTER);
        mensaje.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        dialogo.add(mensaje, java.awt.BorderLayout.CENTER);

        javax.swing.JButton cerrar = new javax.swing.JButton("Entendido");
        cerrar.addActionListener(e -> dialogo.dispose());
        javax.swing.JPanel contenedorBoton = new javax.swing.JPanel();
        contenedorBoton.add(cerrar);
        dialogo.add(contenedorBoton, java.awt.BorderLayout.PAGE_END);

        dialogo.pack();
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    /**
     * Punto de entrada de la aplicación (uso para pruebas o ejecución independiente).
     *
     * @param args argumentos de línea de comando (sin uso)
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new PantPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aniadirProducto;
    public static javax.swing.JPanel basePProducto;
    private javax.swing.JButton btnCategoria;
    private javax.swing.JButton btnToggleEliminar;
    public static javax.swing.JButton facturaBtn;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JLabel lblPrecioTotal;
    private static javax.swing.JLabel lblTotal;
    public javax.swing.JPanel panelCategoria;
    private javax.swing.JPanel panelProducto;
    public javax.swing.JPanel pnlLista;
    private javax.swing.JPanel pnlTotal;
    // End of variables declaration//GEN-END:variables

    /**
     * Modelo estático para la tabla de pedido. Provee utilidades estáticas
     * para añadir filas y consultar/actualizar valores en el `DefaultTableModel`.
     */
    public static class modelo {

        private static final javax.swing.table.DefaultTableModel tabla = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Cantidad", "Producto", "Precio ud.", "Total prod."}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo permitir editar la columna Cantidad (0)
                return column == 0;
            }
        };

        /**
         * Añade una fila al modelo de la tabla.
         *
         * @param fila arreglo con los valores de la fila (Cantidad, Producto, Precio, Total)
         */
        public static void addRow(Object[] fila) {
            tabla.addRow(fila);
        }

        /**
         * Devuelve el número de filas del modelo.
         *
         * @return número de filas
         */
        public static int getRowCount() {
            return tabla.getRowCount();
        }

        /**
         * Obtiene el valor almacenado en una celda específica.
         *
         * @param fila índice de fila
         * @param columna índice de columna
         * @return valor de la celda
         */
        public static Object getValueAt(int fila, int columna) {
            return tabla.getValueAt(fila, columna);
        }

        /**
         * Establece el valor de una celda en el modelo.
         *
         * @param valor valor a establecer
         * @param fila índice de fila
         * @param columna índice de columna
         */
        public static void setValueAt(Object valor, int fila, int columna) {
            tabla.setValueAt(valor, fila, columna);
        }

        /**
         * Devuelve la instancia compartida del `DefaultTableModel` usada por la tabla.
         *
         * @return modelo de tabla
         */
        public static javax.swing.table.DefaultTableModel getModelo() {
            return tabla;
        }

    }

    public boolean isModoEliminacionActivo() {
        return modoEliminacionActivo;
    }

    // Elimina una categoría por su nombre desde la interfaz: quita el componente lateral
    // y el panel de productos asociado, refresca la UI y guarda las categorías.
    public void eliminarCategoriaPorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return;
        }

        // Eliminar de panelCategoria (lado izquierdo)
        for (int i = 0; i < panelCategoria.getComponentCount(); i++) {
            java.awt.Component comp = panelCategoria.getComponent(i);
            if (comp instanceof componentes.Categoria cat && nombre.equals(cat.getName())) {
                panelCategoria.remove(i);
                // Si el siguiente componente es un strut/espaciador, eliminarlo también
                if (i < panelCategoria.getComponentCount()) {
                    java.awt.Component next = panelCategoria.getComponent(i);
                    String cls = next.getClass().getName();
                    if (cls.contains("Box") || cls.contains("Filler")) {
                        panelCategoria.remove(i);
                    }
                }
                break;
            }
        }

        // Eliminar panel de productos correspondiente en basePProducto
        for (int i = 0; i < basePProducto.getComponentCount(); i++) {
            java.awt.Component comp = basePProducto.getComponent(i);
            if (comp instanceof javax.swing.JPanel p && nombre.equals(p.getName())) {
                basePProducto.remove(i);
                break;
            } else if (comp instanceof javax.swing.JScrollPane sp) {
                java.awt.Component view = sp.getViewport().getView();
                if (view instanceof javax.swing.JPanel p2 && nombre.equals(p2.getName())) {
                    basePProducto.remove(i);
                    break;
                }
            }
        }

        // Refrescar interfaz
        panelCategoria.revalidate();
        panelCategoria.repaint();
        basePProducto.revalidate();
        basePProducto.repaint();

        // Guardar cambios en XML a partir de la interfaz actual
        guardarCategoriasDesdeInterfaz();
    }
}
