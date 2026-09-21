package view;

import java.awt.GridLayout;

import javax.swing.*;

import Model.Producto;

public class VentanaEditar extends JFrame {

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JComboBox<String> cmbCategoria;
    private JButton confirmar;
    private JButton cancelar;
    private Producto producto;

    public VentanaEditar(Producto producto, Runnable alConfirmar) {

        this.producto = producto;

        setTitle("Editar producto");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        txtNombre = new JTextField(producto.getNombre());
        txtPrecio = new JTextField(String.valueOf(producto.getPrecio()));
        txtStock = new JTextField(String.valueOf(producto.getStock()));
        cmbCategoria = new JComboBox<>();

        cmbCategoria.addItem("Almacén");
        cmbCategoria.addItem("Bebidas");
        cmbCategoria.addItem("Limpieza");
        cmbCategoria.addItem("Verduleria");
        cmbCategoria.addItem("Otros");

        cmbCategoria.setSelectedItem(producto.getCategoria());

        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Precio:"));
        add(txtPrecio);
        add(new JLabel("Stock:"));
        add(txtStock);
        add(new JLabel("Categoria:"));
        add(cmbCategoria);

        confirmar = new JButton("Confirmar");
        add(confirmar);

        cancelar = new JButton("Cancelar");
        add(cancelar);

       eventoEditar(alConfirmar);

        cancelar.addActionListener(e -> dispose());
       

        setVisible(true);
    }

    public void eventoEditar(Runnable alConfirmar) {

    confirmar.addActionListener(e -> {

        String nuevoNombre = txtNombre.getText();
        double nuevoPrecio = Double.parseDouble(txtPrecio.getText());
        int nuevoStock = Integer.parseInt(txtStock.getText());
        String nuevaCategoria = cmbCategoria.getSelectedItem().toString();

        producto.setName(nuevoNombre);
        producto.setCategoria(nuevaCategoria);
        producto.setPrecio(nuevoPrecio);
        producto.setStock(nuevoStock);

        alConfirmar.run();

        dispose();
    });
}
}