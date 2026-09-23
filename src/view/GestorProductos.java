package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Model.Producto;
import components.ComponenteNombre;

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

    // AGREGAR PRODUCTO
    private void agregarProducto() {

    Producto producto = formulario.crearProducto();

    if (producto == null) {
        return;
    }

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