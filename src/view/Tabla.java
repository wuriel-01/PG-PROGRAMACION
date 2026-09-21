package view;

import java.awt.*;
import javax.swing.*;

import Model.Producto;

public class Tabla extends JPanel {

    private JPanel panelProductos;
    private JLabel lblTotal;
    private double total = 0;


    public Tabla() {

        setLayout(new BorderLayout());

        // Panel donde estarán todos los productos
        panelProductos = new JPanel();

        panelProductos.setLayout(
                new BoxLayout(
                        panelProductos,
                        BoxLayout.Y_AXIS
                )
        );


        // Scroll para cuando haya muchos productos
        JScrollPane scroll = new JScrollPane(panelProductos);


        // Total
        lblTotal = new JLabel(
                "Valor total del stock: $0.00"
        );


        add(scroll, BorderLayout.CENTER);

        add(lblTotal, BorderLayout.SOUTH);
    }


    // ========================================================
    // AGREGAR PRODUCTO
    // ========================================================

    public void agregarProducto(Producto producto) {

        // Panel que representa UN producto
        JPanel panelProducto = new JPanel();

        panelProducto.setLayout(
                new GridLayout(1, 7, 10, 10)
        );


        // Creamos los JLabel
        JLabel lblNombre =
                new JLabel(producto.getNombre());

        JLabel lblPrecio =
                new JLabel("$" + producto.getPrecio());

        JLabel lblStock =
                new JLabel(String.valueOf(producto.getStock()));

        JLabel lblCategoria =
                new JLabel(producto.getCategoria());

        JLabel lblValorStock =
                new JLabel("$" + producto.getValorStock());


        // Botones
        JButton btnEditar =
                new JButton("Editar");

        JButton btnEliminar =
                new JButton("Eliminar");


        // Agregamos todo
        panelProducto.add(lblNombre);
        panelProducto.add(lblPrecio);
        panelProducto.add(lblStock);
        panelProducto.add(lblCategoria);
        panelProducto.add(lblValorStock);

        panelProducto.add(btnEditar);
        panelProducto.add(btnEliminar);


        // Agregamos el producto a la lista
        panelProductos.add(panelProducto);


        // ====================================================
        // EVENTO ELIMINAR
        // ====================================================

        btnEliminar.addActionListener(e -> {

            panelProductos.remove(panelProducto);

            actualizarTotal(-producto.getValorStock());

            panelProductos.revalidate();
            panelProductos.repaint();
        });


        // ====================================================
        // EVENTO EDITAR
        // ====================================================

       btnEditar.addActionListener(e -> {

    double valorAnterior = producto.getValorStock();

    new VentanaEditar(producto, () -> {

        double valorNuevo = producto.getValorStock();

        double diferencia = valorNuevo - valorAnterior;

        actualizarTotal(diferencia);

        lblNombre.setText(producto.getNombre());

        lblPrecio.setText(
                "$" + producto.getPrecio()
        );

        lblStock.setText(
                String.valueOf(producto.getStock())
        );

        lblCategoria.setText(
                producto.getCategoria()
        );

        lblValorStock.setText(
                "$" + producto.getValorStock()
        );
    });
});


        // Actualizamos total
        actualizarTotal(producto.getValorStock());


        // Actualizamos visualmente
        panelProductos.revalidate();
        panelProductos.repaint();
    }


    // ========================================================
    // ACTUALIZAR TOTAL
    // ========================================================

    private void actualizarTotal(double valor) {

        total += valor;

        lblTotal.setText(
                String.format(
                        "Valor total del stock: $%.2f",
                        total
                )
        );
    }
}