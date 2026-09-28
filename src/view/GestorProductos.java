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
        formulario.eventoAgregar(e -> agregarProducto());

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
    // AGREGAR O ACTUALIZAR PRODUCTO
    // ========================================================
    private void agregarProducto() {

        Producto producto = formulario.crearProducto();

        if (producto == null) {
            return;
        }

        // 1. Buscamos si ya existe un producto con el mismo código de barras en la tabla
        Producto productoExistente = tablaProductos.buscarPorCodigoBarras(producto.getCodigoBarras());

        if (productoExistente != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ese código de barras ya está en uso con otro producto.",
                    "Código de barras duplicado",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Si no existe, agregamos el nuevo producto a la tabla
        tablaProductos.agregarProducto(producto);
        formulario.limpiarFormulario();

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