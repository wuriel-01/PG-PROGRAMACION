package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.Producto;
import components.ComponenteBuscador;

public class Tabla extends JPanel {

    private JPanel panelProductos;
    private JLabel lblTotal;

    private double total = 0;

    private ArrayList<Producto> listaProductos;

    private ComponenteBuscador buscador;

    public Tabla() {

        setLayout(new BorderLayout());

        listaProductos = new ArrayList<>();
        panelProductos = new JPanel();

        panelProductos.setLayout(
                new BoxLayout(
                        panelProductos,
                        BoxLayout.Y_AXIS
                )
        );

        buscador = new ComponenteBuscador(panelProductos);
        JScrollPane scroll =
                new JScrollPane(panelProductos);

        lblTotal =
                new JLabel("Valor total del stock: $0.00");
        add(buscador, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(lblTotal, BorderLayout.SOUTH);
    }


    public void agregarProducto(Producto producto) {

        
        JPanel panelProducto = new JPanel();

        panelProducto.setLayout(new GridLayout(1, 7, 10, 10));

        panelProducto.setName(producto.getNombre());

        JLabel lblNombre =new JLabel(producto.getNombre());

        JLabel lblPrecio =new JLabel("$" + producto.getPrecio());

        JLabel lblStock =
                new JLabel( String.valueOf(producto.getStock()));

        JLabel lblCategoria =new JLabel(producto.getCategoria());

        JLabel lblValorStock =new JLabel("$" + producto.getValorStock());

        JButton btnEditar =new JButton("Editar");

        JButton btnEliminar =new JButton("Eliminar");

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

            actualizarTotal( -producto.getValorStock());

            panelProductos.revalidate();
            panelProductos.repaint();
        });


        btnEditar.addActionListener(e -> {

            double valorAnterior =
                    producto.getValorStock();

            new VentanaEditar( producto,() -> {

                        double valorNuevo = producto.getValorStock();

                        double diferencia =valorNuevo - valorAnterior;

                        actualizarTotal(diferencia);

                        lblNombre.setText(
                                producto.getNombre());
                        lblPrecio.setText("$" + producto.getPrecio());

                        lblStock.setText( String.valueOf(producto.getStock()));

                        lblCategoria.setText( producto.getCategoria());

                        lblValorStock.setText("$" + producto.getValorStock());

                        panelProducto.setName(
                                producto.getNombre()
                        );
                    }
            );
        });


        actualizarTotal(
                producto.getValorStock()
        );

        panelProductos.revalidate();
        panelProductos.repaint();
    }

    private void actualizarTotal(double valor) {

        total += valor;

        lblTotal.setText(
                String.format(
                        "Valor total del stock: $%.2f",
                        total));
                }}