/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodetpv;

import componentes.Categoria;
import componentes.NuevoProducto;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author mpvlm
 */
public class categoriaxml {

    private static final String FILE_PATH = "categorias.xml";

    // Guardar categorías y productos en XML
    public static void guardarCategorias(List<Categoria> categorias) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("categorias");
            doc.appendChild(root);

            for (Categoria categoria : categorias) {
                Element categoriaElement = doc.createElement("categoria");
                categoriaElement.setAttribute("nombre", categoria.getNombre());
                root.appendChild(categoriaElement);

                // Guardar productos de la categoría
                for (Producto producto : categoria.getProductos()) {
                    Element productoElement = doc.createElement("producto");
                    productoElement.setAttribute("nombre", producto.getNombre());
                    productoElement.setAttribute("precio", String.valueOf(producto.getPrecio()));
                    productoElement.setAttribute("cantidad", String.valueOf(producto.getCantidad()));
                    productoElement.setAttribute("imagen", producto.getRutaImagen() != null ? producto.getRutaImagen() : "");
                    categoriaElement.appendChild(productoElement);
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(FILE_PATH));
            transformer.transform(source, result);

            System.out.println("Archivo XML creado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cargar categorías y productos desde XML
    public static List<Categoria> cargarCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                System.out.println("El archivo de categorías no existe. Retornando lista vacía.");
                return categorias;
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList categoriaNodes = doc.getElementsByTagName("categoria");
            for (int i = 0; i < categoriaNodes.getLength(); i++) {
                Node node = categoriaNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element categoriaElement = (Element) node;
                    String nombre = categoriaElement.getAttribute("nombre");

                    Categoria nuevaCategoria = new Categoria(nombre);

                    // Cargar productos de esta categoría
                    NodeList productoNodes = categoriaElement.getElementsByTagName("producto");
                    for (int j = 0; j < productoNodes.getLength(); j++) {
                        Element productoElement = (Element) productoNodes.item(j);
                        String nombreProd = productoElement.getAttribute("nombre");
                        float precio = Float.parseFloat(productoElement.getAttribute("precio"));
                        int cantidad = Integer.parseInt(productoElement.getAttribute("cantidad"));
                        String imagen = productoElement.getAttribute("imagen");

                        Producto producto = new Producto(nombreProd, precio, cantidad, imagen);
                        nuevaCategoria.agregarProducto(producto);
                    }

                    categorias.add(nuevaCategoria);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categorias;
    }

    // Eliminar una categoría por nombre y actualizar el archivo XML
    public static boolean eliminarCategoria(String nombre) {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                return false;
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList categoriaNodes = doc.getElementsByTagName("categoria");
            boolean removed = false;
            for (int i = 0; i < categoriaNodes.getLength(); i++) {
                Node node = categoriaNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element categoriaElement = (Element) node;
                    String nombreElem = categoriaElement.getAttribute("nombre");
                    if (nombre.equals(nombreElem)) {
                        categoriaElement.getParentNode().removeChild(categoriaElement);
                        removed = true;
                        break;
                    }
                }
            }

            if (removed) {
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

