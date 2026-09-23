package view;

import java.awt.GridLayout;

import javax.swing.*;

import Model.Producto;
import components.ComponenteCategoria;
import components.ComponenteNombre;
import components.ComponentePrecio;
import components.ComponenteStock;

public class VentanaEditar extends JFrame {

    private ComponenteNombre objetoNombreEditar;
    private ComponentePrecio objetoPrecioEditar;
    private ComponenteStock objetoStockEditar;
    private ComponenteCategoria objetoCategoriaEditar;
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

        objetoNombreEditar = new ComponenteNombre(producto.getNombre());
        objetoPrecioEditar = new ComponentePrecio(producto.getPrecio());
        objetoStockEditar= new ComponenteStock(producto.getStock());
        objetoCategoriaEditar = new ComponenteCategoria(producto.getCategoria());

        add(objetoNombreEditar);
        add(objetoPrecioEditar);
        add(objetoStockEditar);
        add(objetoCategoriaEditar);

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

        String nuevoNombre = objetoNombreEditar.getNombre();
        double nuevoPrecio = objetoPrecioEditar.getPrecio();
        int nuevoStock = objetoStockEditar.getStock();
        String nuevaCategoria = objetoCategoriaEditar.getCategoria();

        producto.setName(nuevoNombre);
        producto.setCategoria(nuevaCategoria);
        producto.setPrecio(nuevoPrecio);
        producto.setStock(nuevoStock);

        alConfirmar.run();

        dispose();
    });
}
}