package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Model.Producto;

public class Tabla extends JPanel {

    private JPanel panelProductos;
    private JTextField txtBuscar;
    private JLabel lblTotal;
    private double total = 0;
    private ArrayList<Producto> listaProductos;

    public Tabla() {

        setLayout(new BorderLayout());

        panelProductos = new JPanel();

        panelProductos.setLayout(
                new BoxLayout(
                        panelProductos,
                        BoxLayout.Y_AXIS
                )
        );

        JScrollPane scroll = new JScrollPane(panelProductos);

        lblTotal = new JLabel(
                "Valor total del stock: $0.00"
        );

        txtBuscar = new JTextField();

        listaProductos = new ArrayList<>();

        add(txtBuscar, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(lblTotal, BorderLayout.SOUTH);

        BuscarProducto();
    }


    public void BuscarProducto() {

        txtBuscar.getDocument().addDocumentListener(
                new DocumentListener() {

                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        filtrarProductos();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        filtrarProductos();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        filtrarProductos();
                    }
                }
        );
    }


    private void filtrarProductos() {

        String nombreBuscado =
                txtBuscar.getText().toLowerCase();

        for (Component componente : panelProductos.getComponents()) {

            String nombreProducto = componente.getName();

            if (nombreProducto != null) {

                if (nombreProducto.toLowerCase().contains(nombreBuscado)) {
                    componente.setVisible(true);
                } else {
                    componente.setVisible(false);
                }
            }
        }

        panelProductos.revalidate();
        panelProductos.repaint();
    }


    public void agregarProducto(Producto producto) {

        JPanel panelProducto = new JPanel();

        panelProducto.setLayout(
                new GridLayout(1, 7, 10, 10)
        );

        panelProducto.setName(producto.getNombre());

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

        JButton btnEditar =
                new JButton("Editar");

        JButton btnEliminar =
                new JButton("Eliminar");

        listaProductos.add(producto);

        panelProducto.add(lblNombre);
        panelProducto.add(lblPrecio);
        panelProducto.add(lblStock);
        panelProducto.add(lblCategoria);
        panelProducto.add(lblValorStock);

        panelProducto.add(btnEditar);
        panelProducto.add(btnEliminar);

        panelProductos.add(panelProducto);


        btnEliminar.addActionListener(e -> {

            panelProductos.remove(panelProducto);

            listaProductos.remove(producto);

            actualizarTotal(-producto.getValorStock());

            panelProductos.revalidate();
            panelProductos.repaint();
        });


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

                panelProducto.setName(producto.getNombre());

                filtrarProductos();
            });
        });


        actualizarTotal(producto.getValorStock());

        panelProductos.revalidate();
        panelProductos.repaint();
    }


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