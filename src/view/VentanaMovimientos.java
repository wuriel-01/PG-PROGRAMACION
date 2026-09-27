package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.Cambio;
import Model.GestorMovimientos;
import Model.Movimientos;
import Model.Producto;

public class VentanaMovimientos extends JFrame {

    private String id;
    private Producto producto;

    private JPanel panelMovimientos;

    public VentanaMovimientos(Producto producto) {

        this.producto = producto;
        this.id = producto.getId();

        setTitle("Movimientos - " + producto.getNombre());
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblProducto = new JLabel(
                "Producto: " + producto.getNombre()
        );

        add(lblProducto, BorderLayout.NORTH);

        panelMovimientos = new JPanel();
        panelMovimientos.setLayout(
                new BoxLayout(panelMovimientos, BoxLayout.Y_AXIS)
        );

        JScrollPane scroll = new JScrollPane(panelMovimientos);

        add(scroll, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");

        btnCerrar.addActionListener(e -> dispose());

        add(btnCerrar, BorderLayout.SOUTH);

        listarMovimientos();

        setVisible(true);
    }

    private void listarMovimientos() {

        ArrayList<Movimientos> historialProducto =
                GestorMovimientos.buscarPorUuid(id);

        if (historialProducto.isEmpty()) {

            panelMovimientos.add(
                    new JLabel("Este producto todavía no tiene movimientos.")
            );

            return;
        }

        for (Movimientos movimiento : historialProducto) {

            JPanel panelMovimiento = new JPanel();
            panelMovimiento.setLayout(
                    new BoxLayout(panelMovimiento, BoxLayout.Y_AXIS)
            );

            JLabel lblFecha = new JLabel(
                    "Fecha: " + movimiento.getFecha()
            );

            panelMovimiento.add(lblFecha);

            for (Cambio cambio : movimiento.getCambios()) {

                JPanel fila = new JPanel(
                        new GridLayout(1, 3, 10, 5)
                );

                JLabel lblCampo =
                        new JLabel(cambio.getCampo());

                JLabel lblAnterior =
                        new JLabel(cambio.getValorAnterior());

                JLabel lblNuevo =
                        new JLabel(cambio.getValorNuevo());

                fila.add(lblCampo);
                fila.add(lblAnterior);
                fila.add(lblNuevo);

                panelMovimiento.add(fila);
            }

            panelMovimientos.add(panelMovimiento);
        }

        panelMovimientos.revalidate();
        panelMovimientos.repaint();
    }
}