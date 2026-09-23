package view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Producto;
import components.ComponenteCategoria;
import components.ComponenteNombre;
import components.ComponentePrecio;
import components.ComponenteStock;

public class FormularioProducto extends JPanel {

    private ComponenteNombre objetoNombre;
    private ComponenteStock  objetoStock;
    private ComponentePrecio  objetoPrecio;
    private ComponenteCategoria objetoCategoria;
   
   
    private JButton btnAgregar;
    private JButton btnLimpiar;

    public FormularioProducto() {

        setLayout(new GridLayout(5, 2, 10, 10));

        objetoNombre = new ComponenteNombre();
        objetoStock= new ComponenteStock();
        objetoPrecio= new ComponentePrecio();
        objetoCategoria= new ComponenteCategoria();
        

        add(objetoNombre);
        add(objetoPrecio);
        add(objetoStock);
        add(objetoCategoria);

        btnAgregar = new JButton("Agregar");
        add(btnAgregar);

        btnLimpiar = new JButton("Limpiar");
        add(btnLimpiar);

        eventoLimpiar();
    }

   
    public void eventoLimpiar() {
        btnLimpiar.addActionListener(e->limpiarFormulario());
    }


   public void eventoAgregar(ActionListener evento) {
    btnAgregar.addActionListener(evento);
}

public Producto crearProducto() {

    if (!objetoNombre.validacionNombre()) {
        return null;
    }

    if (!objetoPrecio.validacionPrecio()) {
        return null;
    }

    if (!objetoStock.validacionStock()) {
        return null;
    }

    String nombre = objetoNombre.getNombre();
    double precio = objetoPrecio.getPrecio();
    int stock = objetoStock.getStock();
    String categoria = objetoCategoria.getCategoria();

    Producto producto = new Producto(
            nombre,
            precio,
            stock,
            categoria
    );

    return producto;

}
    public void limpiarFormulario(){
            objetoNombre.limpiarNombre();
            objetoStock.limpiarStock();
            objetoPrecio.limpiarPrecio();
        }

   

}