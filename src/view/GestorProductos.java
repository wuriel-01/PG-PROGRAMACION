package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Model.Producto;

public class GestorProductos extends JFrame {

    // Componentes principales de nuestra ventana
    private FormularioProducto formulario;
    private Tabla tablaProductos;


    // ========================================================
    // CONSTRUCTOR
    // ========================================================

    public GestorProductos() {

        // Creamos el formulario y la tabla
        formulario = new FormularioProducto();
        tablaProductos = new Tabla();

        // Configuración de la ventana
        setTitle("Gestor de Productos");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creamos la interfaz
        crearInterfaz();

        // Configuramos los eventos
        crearEventos();
    }


    // ========================================================
    // CREAR INTERFAZ
    // ========================================================

    private void crearInterfaz() {

        setLayout(new BorderLayout());

        // Formulario arriba
        add(formulario, BorderLayout.NORTH);

        // Tabla en el centro
        add(tablaProductos, BorderLayout.CENTER);
    }


    // ========================================================
    // CREAR EVENTOS
    // ========================================================

    private void crearEventos() {

        // Cuando presionamos Agregar
        formulario.eventoAgregar(e -> agregarProducto());
        formulario.eventoLimpiar();
    }


    // ========================================================
    // AGREGAR PRODUCTO
    // ========================================================

    private void agregarProducto() {

        // Obtenemos los datos del formulario
        String nombre = formulario.getNombre();
        String precioTexto = formulario.getPrecio();
        String stockTexto = formulario.getStock();
        String categoria = formulario.getCategoria();


        // ====================================================
        // VALIDAR NOMBRE
        // ====================================================

        if (nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el nombre del producto.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            formulario.validacionNombre();
            return;
        }


        double precio;
        int stock;


        // ====================================================
        // CONVERTIR PRECIO
        // ====================================================

        try {

            precio = Double.parseDouble(precioTexto);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El precio debe ser un número válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            formulario.validacionPrecio();
            return;
        }


        // ====================================================
        // CONVERTIR STOCK
        // ====================================================

        try {

            stock = Integer.parseInt(stockTexto);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El stock debe ser un número entero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
             formulario.validacionStock();
            return;
        }


        // ====================================================
        // VALIDAR PRECIO
        // ====================================================

        if (precio <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "El precio debe ser mayor que cero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // ====================================================
        // VALIDAR STOCK
        // ====================================================

        if (stock < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "El stock no puede ser negativo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // ====================================================
        // CREAR PRODUCTO
        // ====================================================

        Producto producto = new Producto(
                nombre,
                precio,
                stock,
                categoria
        );


        // ====================================================
        // AGREGAR PRODUCTO A LA TABLA
        // ====================================================

        tablaProductos.agregarProducto(producto);


        // ====================================================
        // LIMPIAR FORMULARIO
        // ====================================================

        formulario.limpiar();;


        // ====================================================
        // MENSAJE
        // ====================================================

        JOptionPane.showMessageDialog(
                this,
                "Producto agregado correctamente.",
                "Información",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // ========================================================
    // MAIN
    // ========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            GestorProductos ventana = new GestorProductos();

            ventana.setVisible(true);
        });
    }
}