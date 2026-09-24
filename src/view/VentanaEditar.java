package view;

import java.awt.GridLayout;

import javax.swing.*;

import Model.Producto;
import components.ComponenteCategoria;
import components.ComponenteCodigoBarras;
import components.ComponenteNombre;
import components.ComponentePrecio;
import components.ComponenteStock;

public class VentanaEditar extends JFrame {

    private ComponenteNombre objetoNombreEditar;
    private ComponentePrecio objetoPrecioEditar;
    private ComponenteStock objetoStockEditar;
    private ComponenteCategoria objetoCategoriaEditar;
    private ComponenteCodigoBarras objetoCodigoBarrasEditar;
    private JButton confirmar;
    private JButton cancelar;
    private Producto producto;

    public VentanaEditar(Producto producto, Runnable alConfirmar) {

        this.producto = producto;

        setTitle("Editar producto");
        setSize(460, 390);
        setLayout(new GridLayout(0, 1, 10, 8));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        objetoNombreEditar = new ComponenteNombre(producto.getNombre());
        objetoPrecioEditar = new ComponentePrecio(producto.getPrecio());
        objetoStockEditar= new ComponenteStock(producto.getStock());
        objetoCategoriaEditar = new ComponenteCategoria(producto.getCategoria());
        objetoCodigoBarrasEditar = new ComponenteCodigoBarras(producto.getCodigoBarras());

        add(objetoNombreEditar);
        add(objetoCodigoBarrasEditar);
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

        if (!objetoNombreEditar.validacionNombre()
                || !objetoPrecioEditar.validacionPrecio()
                || !objetoStockEditar.validacionStock()
                || !objetoCategoriaEditar.validacionCategoria()
                || !objetoCodigoBarrasEditar.validacionCodigoBarras()) {
            return;
        }

        String nuevoNombre = objetoNombreEditar.getNombre();
        double nuevoPrecio = objetoPrecioEditar.getPrecio();
        int nuevoStock = objetoStockEditar.getStock();
        String nuevaCategoria = objetoCategoriaEditar.getCategoria();

        producto.setNombre(nuevoNombre);
        producto.setCodigoBarras(objetoCodigoBarrasEditar.getCodigoBarras());
        producto.setCategoria(nuevaCategoria);
        producto.setPrecio(nuevoPrecio);
        producto.setStock(nuevoStock);

        alConfirmar.run();

        dispose();
    });
}
}
