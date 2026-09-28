package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Model.Producto;


public class Main extends JFrame {

    // Componentes principales de nuestra ventana
    private FormularioProducto formulario;
    private Tabla tablaProductos;

    public Main() {

        
        formulario = new FormularioProducto();
        tablaProductos = new Tabla();

        
        setTitle("Gestor de Productos");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        crearInterfaz();
        formulario.eventoAgregar(e -> agregarProducto());

    }



    private void crearInterfaz() {

        setLayout(new BorderLayout());

        // Formulario arriba
        add(formulario, BorderLayout.NORTH);

        // Tabla en el centro
        add(tablaProductos, BorderLayout.CENTER);
    }

 
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

       
        tablaProductos.agregarProducto(producto);
        formulario.limpiarFormulario();

        JOptionPane.showMessageDialog(
                this,
                "Producto agregado correctamente.",
                "Información",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Main ventana = new Main();

            ventana.setVisible(true);
        });
    }
}