/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodetpv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * Utilidad para exportar el contenido de una tabla Swing a un archivo HTML.
 *
 * Genera una factura sencilla en HTML con los valores de la tabla y un
 * recuento del total de la factura (se asume que la última columna es el total
 * por fila).
 *
 * @author mpvlm
 */
public class HtmlExportarPedidos {
    /**
     * Exporta el contenido de la tabla dada a un fichero HTML.
     *
     * @param tabla componente `JTable` cuyo contenido se exportará
     * @param destino fichero destino donde se escribirá el HTML
     * @throws IOException si ocurre un error de E/S durante la escritura
     */
    public static void exportarTablaComoHtml(JTable tabla, File destino) throws IOException {
        TableModel model = tabla.getModel();
        LocalDateTime ahora = LocalDateTime.now();
        String fechaHoraCobro = ahora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n<html lang=\"es\">\n<head>\n<meta charset=\"UTF-8\"/>\n")
                .append("<title>Factura TPV</title>\n")
                .append("<style>")
                .append("body{font-family:Arial, sans-serif;margin:20px;}")
                .append("header{margin-bottom:16px;}")
                .append("table{border-collapse:collapse;width:100%;}")
                .append("th,td{border:1px solid #ddd;padding:8px;text-align:left;}")
                .append("th{background:#f3f3f3;}")
                .append(".totales{margin-top:16px;font-weight:bold;}")
                .append("</style>\n</head>\n<body>\n");

        sb.append("<header>")
                .append("<h1>Factura TPV</h1>")
                .append("<p><strong>Fecha y hora de cobro:</strong> ").append(fechaHoraCobro).append("</p>")
                .append("</header>\n");

        sb.append("<table>\n<thead><tr>");
        for (int c = 0; c < model.getColumnCount(); c++) {
            sb.append("<th>").append(escape(model.getColumnName(c))).append("</th>");
        }
        sb.append("</tr></thead>\n<tbody>\n");

        double totalFactura = 0.0;
        // Se asume que la columna "Total" es la última. Ajusta si tu índice es diferente.
        int indiceTotal = model.getColumnCount() - 1;

        for (int r = 0; r < model.getRowCount(); r++) {
            sb.append("<tr>");
            for (int c = 0; c < model.getColumnCount(); c++) {
                Object val = model.getValueAt(r, c);
                sb.append("<td>").append(escape(String.valueOf(val))).append("</td>");
            }
            Object totalCelda = model.getValueAt(r, indiceTotal);
            try {
                totalFactura += Double.parseDouble(String.valueOf(totalCelda));
            } catch (Exception ignored) {
            }
            sb.append("</tr>\n");
        }
        sb.append("</tbody>\n</table>\n");

        sb.append("<div class=\"totales\">Total factura: ")
                .append(String.format("%.2f €", totalFactura))
                .append("</div>\n");

        sb.append("</body>\n</html>");

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(destino), StandardCharsets.UTF_8)) {
            writer.write(sb.toString());
        }
    }

    private static String escape(String s) {
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
