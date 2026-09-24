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
            // 2. Si ya existe, le sumamos el stock nuevo
            int nuevoStock = productoExistente.getStock() + producto.getStock();
            productoExistente.setStock(nuevoStock);

            // Actualizamos la tabla para reflejar los cambios
            tablaProductos.actualizarTabla();

            JOptionPane.showMessageDialog(
                    this,
                    "El código de barras ya existe. Se han sumado " + producto.getStock() + " unidades al stock actual (Stock total: " + nuevoStock + ").",
                    "Stock Actualizado",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            // 3. Si no existe, agregamos el nuevo producto a la tabla
            tablaProductos.agregarProducto(producto);

            JOptionPane.showMessageDialog(
                    this,
                    "Producto agregado correctamente.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        // Limpiamos el formulario tras agregar o actualizar
        formulario.limpiarFormulario();
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