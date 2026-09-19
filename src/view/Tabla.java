package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Producto;

public class Tabla extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnEliminar;
    private JLabel lblTotal;

    public Tabla() {

        setLayout(new BorderLayout());

        String[] columnas = {
                "Nombre",
                "Precio",
                "Stock",
                "Categoría",
                "Valor Stock"
        };

        modelo = new DefaultTableModel(columnas, 0);

        tabla = new JTable(modelo);

        JScrollPane scrollTabla = new JScrollPane(tabla);

        btnEliminar = new JButton("Eliminar");

        lblTotal = new JLabel(
                "Valor total del stock: $0.00");
        JPanel panelInferior = new JPanel( new BorderLayout());

        panelInferior.add(btnEliminar, BorderLayout.WEST);
        panelInferior.add(lblTotal,BorderLayout.EAST);
        add(scrollTabla,BorderLayout.CENTER);
        add(panelInferior,BorderLayout.SOUTH);

        btnEliminar.addActionListener(
                e -> eliminarProducto()
        );
    }

    public void agregarProducto(Producto producto) {

        modelo.addRow(new Object[]{
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCategoria(),
                producto.getValorStock()
        });
        actualizarTotal();
    }




    private void eliminarProducto() {

        int filaSeleccionada =
                tabla.getSelectedRow();


        if (filaSeleccionada == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un producto.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        
        int respuesta =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de eliminar el producto?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );


        // Si respondió SÍ
        if (respuesta == JOptionPane.YES_OPTION) {

            modelo.removeRow(filaSeleccionada);

            // Actualizamos el total
            actualizarTotal();
        }
    }




    private void actualizarTotal() {
        double total = 0;
  
        for (int i = 0; i < modelo.getRowCount(); i++) {

            double valor =
                    Double.parseDouble(
                            modelo
                                    .getValueAt(i, 4)
                                    .toString()
                    );

            total += valor;
        }
        lblTotal.setText(
                String.format(
                        "Valor total del stock: $%.2f",
                        total
                )
        );
    }
}